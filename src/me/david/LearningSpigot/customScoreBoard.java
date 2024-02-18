package me.david.LearningSpigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.*;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class customScoreBoard {
        static LearningSpigot plugin;

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        final Scoreboard board = manager.getNewScoreboard();
        final Team team1 = board.registerNewTeam("Team1");
        final Team team2 = board.registerNewTeam("Team2");
        private ArrayList<Player> team1List = new ArrayList<Player>();
        private ArrayList<Player> team2List = new ArrayList<Player>();
        private ArrayList<Player> playerArrayList = new ArrayList<>();
    Objective objective = board.registerNewObjective("test", "dummy");



    public customScoreBoard() {
        playerArrayList.addAll(Bukkit.getOnlinePlayers());
        setTeams();
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("Kills");
    }


        @EventHandler
        public void onPlayerDeath(PlayerDeathEvent e) {
            Player event = e.getEntity().getPlayer();
            Player killer = e.getEntity().getKiller();
            Score score = objective.getScore(ChatColor.GREEN + "Kills: ");
            score.setScore(33);
        }

        public void setTeams() {
            Random rand = new Random();
            for (int i = 0; i < playerArrayList.size(); i++) {
                Player target = playerArrayList.get(i);

                if (rand.nextInt(100) > 50) {
                    team1List.add(target);
                    playerArrayList.remove(target);
                } else if (rand.nextInt(100) <= 50) {
                    team2List.add(target);
                    playerArrayList.remove(target);
                }
                target.setScoreboard(board);
            }
            team1.setDisplayName("Team 1");
            team2.setDisplayName("Team 2");
            team1.setAllowFriendlyFire(false);
            team2.setAllowFriendlyFire(false);
        }




}