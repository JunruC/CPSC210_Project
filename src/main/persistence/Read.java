package persistence;

import model.Player;
import model.Team;
import model.Trade;
import model.TradeList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// Represent a reading tool that read the data in the json files.
public class Read {

    // Effects: construct a reading tool.
    public Read() {
    }

    // Effects: read pending trades in the file.
    public void readPendingTrades(TradeList tl, String fileName) throws IOException {
        String pendingTradesString = new String(Files.readAllBytes(Paths.get("./data/" + fileName)));
        JSONArray pendingTradesData = new JSONArray(pendingTradesString);
        for (int i = 0; i < pendingTradesData.length(); i++) {
            JSONArray pendingTradeData = pendingTradesData.getJSONArray(i);
            Trade t = new Trade();
            for (int j = 0; j < pendingTradeData.length(); j++) {
                JSONObject playerData = pendingTradeData.getJSONObject(j);
                Player p = readPlayerData(playerData);
                t.addPlayerToTrade(p);
            }
            tl.getPendingTrades().add(t);
        }
    }

    // Effects: read completed trades in the file.
    public void readCompletedTrades(TradeList tl, String fileName) throws IOException {
        String completedTradesString = new String(Files.readAllBytes(Paths.get("./data/" + fileName)));
        JSONArray completedTradesData = new JSONArray(completedTradesString);
        for (int i = 0; i < completedTradesData.length(); i++) {
            JSONArray completedTradeData = completedTradesData.getJSONArray(i);
            Trade t = new Trade();
            for (int j = 0; j < completedTradeData.length(); j++) {
                JSONObject playerData = completedTradeData.getJSONObject(j);
                Player p = readPlayerData(playerData);
                t.addPlayerToTrade(p);
            }
            tl.getCompletedTrades().add(t);
        }
    }

    // Effects: read first side teams of pending trades in the file.
    public void readPendingTeamOne(TradeList tl, String fileName) throws IOException {
        String pendingTeamOneString = new String(Files.readAllBytes(Paths.get("./data/" + fileName)));
        JSONArray pendingTeamOneData = new JSONArray(pendingTeamOneString);
        for (int i = 0; i < pendingTeamOneData.length(); i += 2) {
            JSONObject teamNameData = pendingTeamOneData.getJSONObject(i);
            String teamName = teamNameData.getString("teamName");
            Team t = new Team(teamName);
            JSONArray playersData = pendingTeamOneData.getJSONArray(i + 1);
            for (int j = 0; j < playersData.length(); j++) {
                JSONObject playerData = playersData.getJSONObject(j);
                Player p = readPlayerData(playerData);
                t.getPlayers().add(p);
            }
            tl.getPendingTeamOne().add(t);
        }
    }

    // Effects: read second side teams of pending trades in the file.
    public void readPendingTeamTwo(TradeList tl, String fileName) throws IOException {
        String pendingTeamTwoString = new String(Files.readAllBytes(Paths.get("./data/" + fileName)));
        JSONArray pendingTeamTwoData = new JSONArray(pendingTeamTwoString);
        for (int i = 0; i < pendingTeamTwoData.length(); i += 2) {
            JSONObject teamNameData = pendingTeamTwoData.getJSONObject(i);
            String teamName = teamNameData.getString("teamName");
            Team t = new Team(teamName);
            JSONArray playersData = pendingTeamTwoData.getJSONArray(i + 1);
            for (int j = 0; j < playersData.length(); j++) {
                JSONObject playerData = playersData.getJSONObject(j);
                Player p = readPlayerData(playerData);
                t.getPlayers().add(p);
            }
            tl.getPendingTeamTwo().add(t);
        }
    }

    // Effects: read first side teams of completed trades in the file.
    public void readCompletedTeamOne(TradeList tl, String fileName) throws IOException {
        String completedTeamOneString = new String(Files.readAllBytes(Paths.get("./data/" + fileName)));
        JSONArray completedTeamOneData = new JSONArray(completedTeamOneString);
        for (int i = 0; i < completedTeamOneData.length(); i += 2) {
            JSONObject teamNameData = completedTeamOneData.getJSONObject(i);
            String teamName = teamNameData.getString("teamName");
            Team t = new Team(teamName);
            JSONArray playersData = completedTeamOneData.getJSONArray(i + 1);
            for (int j = 0; j < playersData.length(); j++) {
                JSONObject playerData = playersData.getJSONObject(j);
                Player p = readPlayerData(playerData);
                t.getPlayers().add(p);
            }
            tl.getCompletedTeamOne().add(t);
        }
    }

    // Effects: read second side teams of completed trades in the file.
    public void readCompletedTeamTwo(TradeList tl, String fileName) throws IOException {
        String completedTeamTwoString = new String(Files.readAllBytes(Paths.get("./data/" + fileName)));
        JSONArray completedTeamTwoData = new JSONArray(completedTeamTwoString);
        for (int i = 0; i < completedTeamTwoData.length(); i += 2) {
            JSONObject teamNameData = completedTeamTwoData.getJSONObject(i);
            String teamName = teamNameData.getString("teamName");
            Team t = new Team(teamName);
            JSONArray playersData = completedTeamTwoData.getJSONArray(i + 1);
            for (int j = 0; j < playersData.length(); j++) {
                JSONObject playerData = playersData.getJSONObject(j);
                Player p = readPlayerData(playerData);
                t.getPlayers().add(p);
            }
            tl.getCompletedTeamTwo().add(t);
        }
    }

    // Effects: read successes or failures of trades in the file.
    public void readSuccess(TradeList tl, String fileName) throws IOException {
        String successesDataString = new String(Files.readAllBytes(Paths.get("./data/" + fileName)));
        JSONArray successesData = new JSONArray(successesDataString);
        for (int i = 0; i < successesData.length(); i++) {
            JSONObject successData = successesData.getJSONObject(i);
            boolean success = successData.getBoolean("success");
            tl.getSuccess().add(success);
        }
    }

    // Effects: read the data of the player from playerData and return that player.
    public Player readPlayerData(JSONObject playerData) {
        String team = playerData.getString("team");
        String name = playerData.getString("name");
        String position = playerData.getString("position");
        int age = playerData.getInt("age");
        int height = playerData.getInt("height");
        double weight = playerData.getDouble("weight");
        double salary = playerData.getDouble("salary");
        Player p = new Player(team,name,position,age,height,weight,salary);
        return p;
    }
}
