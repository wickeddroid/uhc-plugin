package io.github.wickeddroid.plugin.message;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@SuppressWarnings("")
@ConfigSerializable
public class Messages {
  private String prefix = "<gradient:#FEE679:#E5BF18>UHC ➣";
  private Team team = new Team();
  private Other other = new Other();
  private Game game = new Game();

  public @NonNull Team team() {
    return this.team;
  }

  public @NonNull Other other() {
    return this.other;
  }

  public @NonNull Game game() {
    return this.game;
  }

  public @NonNull String prefix() {
    return this.prefix;
  }

  @ConfigSerializable
  public static class Game {
    private String hasStarted = "El juego ya ha empezado.";

    public @NonNull String hasStarted() {
      return this.hasStarted;
    }
  }

  @ConfigSerializable
  public static class Team {
    private String create = "Tu equipo se ha creado correctamente con el nombre <color:#93FF9E><param-name>.";
    private String remove = "Se ha disuelto el equipo correctamente.";
    private String join = "Te has unido al equipo <color:#93FF9E><param-name>";
    private String joinPlayer = "Se ha unido el jugador <color:#93FF9E><param-player>";
    private String leave = "Te has salido del equipo <color:#93FF9E><param-name>";
    private String leavePlayer = "El jugador <color:#93FF9E><param-player> <white>ha abandonado tu equipo.";
    private String leaveAsLeader = "No puedes abandonar tu propio equipo. Usa /team disband o /team promote";
    private String inviterEqualsPlayer = "No puedes invitarte a ti mismo.";
    private String leaderAsMember = "No eres el lider del equipo.";
    private String alreadyExists = "Ya te encuentras en un equipo.";
    private String doesNotExist = "No te encuentras en un equipo.";
    private String playerTeamExists = "El jugador ya se encuentra en un equipo.";
    private String playerDoesNotTeamExist = "El jugador no se encuentra en un equipo.";
    private String leaderInvitePlayer = "El jugador <color:#93FF9E><param-player> <white>ha sido invitado correctamente.";
    private String invitePlayer = "El jugador <color:#93FF9E><param-leader> <white>te ha invitado a su equipo";
    private String inviteDoesNotExist = "No se te ha invitado al equipo <color:#93FF9E><param-name>";
    private String leaderCancelInvite = "Se ha cancelado la invitación del jugador <color:#93FF9E><param-player>";
    private String cancelInvite = "Se ha cancelado la invitación del equipo <color:#93FF9E><param-name>.";
    private String message = "[Team] <param-player> > <param-message>";

    public @NonNull String create() {
      return this.create;
    }

    public @NonNull String remove() {
      return this.remove;
    }

    public @NonNull String join() {
      return this.join;
    }

    public @NonNull String joinPlayer() {
      return this.joinPlayer;
    }

    public @NonNull String inviteDoesNotExist() {
      return this.inviteDoesNotExist;
    }

    public @NonNull String leaderAsMember() {
      return this.leaderAsMember;
    }

    public @NonNull String alreadyExists() {
      return this.alreadyExists;
    }

    public @NonNull String leave() {
      return this.leave;
    }

    public @NonNull String leavePlayer() {
      return this.leavePlayer;
    }

    public @NonNull String leaveAsLeader() {
      return this.leaveAsLeader;
    }

    public @NonNull String cancelInvite() {
      return this.cancelInvite;
    }

    public @NotNull String leaderCancelInvite() {
      return this.leaderCancelInvite;
    }

    public @NonNull String invitePlayer() {
      return this.invitePlayer;
    }

    public @NonNull String leaderInvitePlayer() {
      return this.leaderInvitePlayer;
    }

    public @NonNull String doesNotExist() {
      return this.doesNotExist;
    }

    public @NonNull String playerTeamExist() {
      return this.playerTeamExists;
    }

    public @NonNull String playerDoesNotTeamExist() {
      return this.playerDoesNotTeamExist;
    }

    public @NonNull String inviterEqualsPlayer() {
      return this.inviterEqualsPlayer;
    }

    public @NonNull String message() {
      return this.message;
    }
  }

  @ConfigSerializable
  public static class Other {
    private String teamChatOn = "Te has cambiado al chat de equipo.";
    private String teamChatOff = "Te has cambiado al chat global.";
    private String gameHasStarted = "El juego ya ha iniciado";

    public @NonNull String teamChatOn() {
      return this.teamChatOn;
    }

    public @NonNull String teamChatOff() {
      return this.teamChatOff;
    }

    public @NonNull String gameHasStarted() {
      return this.gameHasStarted;
    }
  }
}