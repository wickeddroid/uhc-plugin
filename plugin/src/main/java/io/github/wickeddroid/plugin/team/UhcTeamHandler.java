package io.github.wickeddroid.plugin.team;

import io.github.wickeddroid.api.cache.Cache;
import io.github.wickeddroid.api.game.UhcGame;
import io.github.wickeddroid.api.game.UhcGameState;
import io.github.wickeddroid.plugin.cache.DynamicCache;
import io.github.wickeddroid.plugin.message.MessageHandler;
import io.github.wickeddroid.plugin.message.Messages;
import io.github.wickeddroid.plugin.player.UhcPlayerRegistry;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import team.unnamed.inject.Inject;
import team.unnamed.inject.Singleton;

import java.util.concurrent.TimeUnit;

@Singleton
public class UhcTeamHandler {
  @Inject private UhcGame uhcGame;
  @Inject private Messages messages;
  @Inject private MessageHandler messageHandler;
  @Inject private UhcTeamManager uhcTeamManager;
  @Inject private UhcPlayerRegistry uhcPlayerRegistry;

  private final Cache<String, String> inviteCache = new DynamicCache<>(20, TimeUnit.SECONDS);

  public void addPlayerToTeam(
          final Player leader,
          final Player player
  ) {
    final var playerName = player.getName();
    final var uhcTeam = this.uhcTeamManager.getTeamByPlayer(leader.getName());
    final var uhcPlayer = this.uhcPlayerRegistry.getPlayer(playerName);
    final var teamInvite = this.inviteCache.get(player.getName());

    if (teamInvite == null) {
      this.messageHandler.send(player, this.messages.team().inviteDoesNotExist(), uhcTeam.getName());
      return;
    }

    if (uhcTeam == null) {
      this.messageHandler.send(player, this.messages.team().playerDoesNotTeamExist());
      return;
    }

    if (!teamInvite.equals(uhcTeam.getName())) {
      this.messageHandler.send(player, this.messages.team().inviteDoesNotExist(), uhcTeam.getName());
      return;
    }

    if (this.uhcTeamManager.getTeamByPlayer(player.getName()) != null) {
      this.messageHandler.send(player, this.messages.team().alreadyExists());
      return;
    }

    this.messageHandler.send(leader, this.messages.team().joinPlayer(), playerName);
    this.messageHandler.send(player, this.messages.team().join(), uhcTeam.getName());
    this.inviteCache.invalidate(playerName);

    uhcPlayer.setUhcTeam(uhcTeam);
    uhcTeam.addMember(playerName);
  }

  public void removePlayerOfTeam(
          final Player player
  ) {
    final var playerName = player.getName();

    final var uhcTeam = this.uhcTeamManager.getTeamByPlayer(playerName);
    final var uhcPlayer = this.uhcPlayerRegistry.getPlayer(playerName);

    if (uhcTeam == null) {
      this.messageHandler.send(player, this.messages.team().doesNotExist());
      return;
    }

    final var leaderName = uhcTeam.getLeader();
    final var leader = Bukkit.getPlayer(leaderName);

    if (leaderName.equals(playerName)) {
      this.messageHandler.send(player, this.messages.team().leaveAsLeader());
      return;
    }

    if (leader != null && leader.isOnline()) {
      this.messageHandler.send(leader, this.messages.team().leavePlayer(), playerName);
    }

    this.messageHandler.send(player, this.messages.team().leave(), uhcTeam.getName());
    uhcPlayer.setUhcTeam(null);
    uhcTeam.removeMember(playerName);
  }

  public void invitePlayerToTeam(
          final Player leader,
          final Player player
  ) {
    final var playerName = player.getName();
    final var uhcTeam = this.uhcTeamManager.getTeamByLeader(leader.getName());
    final var invite = this.inviteCache.get(playerName);

    if (this.uhcGame.getUhcGameState() != UhcGameState.WAITING) {
      this.messageHandler.send(leader, this.messages.other().gameHasStarted());
      return;
    }

    if (leader.getName().equals(leader.getName())) {
      this.messageHandler.send(leader, this.messages.team().inviterEqualsPlayer());
      return;
    }

    if (invite != null) {
      this.messageHandler.send(leader, this.messages.team().leaderCancelInvite(), playerName);
      this.messageHandler.send(player, this.messages.team().cancelInvite(), playerName);
      this.inviteCache.invalidate(playerName);
      return;
    }

    if (uhcTeam == null) {
      this.messageHandler.send(leader, this.messages.team().doesNotExist());
      return;
    }

    if (this.uhcTeamManager.getTeamByPlayer(player.getName()) != null) {
      this.messageHandler.send(leader, this.messages.team().playerTeamExist());
      return;
    }

    if (!leader.getName().equals(uhcTeam.getLeader())) {
      this.messageHandler.send(leader, this.messages.team().leaderAsMember());
      return;
    }

    this.messageHandler.send(leader, this.messages.team().leaderInvitePlayer(), playerName);
    this.messageHandler.send(player, this.messages.team().invitePlayer(), leader.getName());
    this.inviteCache.save(playerName, uhcTeam.getName());
  }
}