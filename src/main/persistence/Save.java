package persistence;

import model.Player;
import model.Team;
import model.Trade;
import model.TradeList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represent a saving tool that saves the data in the trade list.
public class Save {

    // Effects: construct a saving tool.
    public Save() {
    }

    // Effects: save pending trades in the trade list.
    public void savePendingTrades(TradeList tl, String fileName) throws FileNotFoundException {
        JSONArray pendingTradesData = new JSONArray();
        for (Trade pendingTrade : tl.getPendingTrades()) {
            JSONArray pendingTradeData = new JSONArray();
            for (Player player : pendingTrade.getTradedPlayers()) {
                JSONObject playerData = new JSONObject();
                savePlayerData(playerData, player);
                pendingTradeData.put(playerData);
            }
            pendingTradesData.put(pendingTradeData);
        }
        String pendingTradesDataString = pendingTradesData.toString();
        PrintWriter pendingTradesFile = new PrintWriter("./data/" + fileName);
        pendingTradesFile.write(pendingTradesDataString);
        pendingTradesFile.close();
    }

    // Effects: save completed trades in the trade list.
    public void saveCompletedTrades(TradeList tl, String fileName) throws FileNotFoundException {
        JSONArray completedTradesData = new JSONArray();
        for (Trade completedTrade : tl.getCompletedTrades()) {
            JSONArray completedTradeData = new JSONArray();
            for (Player player : completedTrade.getTradedPlayers()) {
                JSONObject playerData = new JSONObject();
                savePlayerData(playerData, player);
                completedTradeData.put(playerData);
            }
            completedTradesData.put(completedTradeData);
        }
        String completedTradesDataString = completedTradesData.toString();
        PrintWriter completedTradesFile = new PrintWriter("./data/" + fileName);
        completedTradesFile.write(completedTradesDataString);
        completedTradesFile.close();
    }

    // Effects: save first side teams of pending trades.
    public void savePendingTeamOne(TradeList tl, String fileName) throws FileNotFoundException {
        JSONArray pendingTeamOneData = new JSONArray();
        for (Team pendingTeam : tl.getPendingTeamOne()) {
            JSONObject teamNameData = new JSONObject();
            teamNameData.put("teamName", pendingTeam.getTeamName());
            JSONArray playersData = new JSONArray();
            for (Player player : pendingTeam.getPlayers()) {
                JSONObject playerData = new JSONObject();
                savePlayerData(playerData, player);
                playersData.put(playerData);
            }
            pendingTeamOneData.put(teamNameData);
            pendingTeamOneData.put(playersData);
        }
        String pendingTeamOneDataString = pendingTeamOneData.toString();
        PrintWriter pendingTeamOneFile = new PrintWriter("./data/" + fileName);
        pendingTeamOneFile.write(pendingTeamOneDataString);
        pendingTeamOneFile.close();
    }

    // Effects: save second side teams of pending trades.
    public void savePendingTeamTwo(TradeList tl, String fileName) throws FileNotFoundException {
        JSONArray pendingTeamTwoData = new JSONArray();
        for (Team pendingTeam : tl.getPendingTeamTwo()) {
            JSONObject teamNameData = new JSONObject();
            teamNameData.put("teamName", pendingTeam.getTeamName());
            JSONArray playersData = new JSONArray();
            for (Player player : pendingTeam.getPlayers()) {
                JSONObject playerData = new JSONObject();
                savePlayerData(playerData, player);
                playersData.put(playerData);
            }
            pendingTeamTwoData.put(teamNameData);
            pendingTeamTwoData.put(playersData);
        }
        String pendingTeamTwoDataString = pendingTeamTwoData.toString();
        PrintWriter pendingTeamTwoFile = new PrintWriter("./data/" + fileName);
        pendingTeamTwoFile.write(pendingTeamTwoDataString);
        pendingTeamTwoFile.close();
    }

    // Effects: save first side teams of completed trades.
    public void saveCompletedTeamOne(TradeList tl, String fileName) throws FileNotFoundException {
        JSONArray completedTeamOneData = new JSONArray();
        for (Team completedTeam : tl.getCompletedTeamOne()) {
            JSONObject teamNameData = new JSONObject();
            teamNameData.put("teamName", completedTeam.getTeamName());
            JSONArray playersData = new JSONArray();
            for (Player player : completedTeam.getPlayers()) {
                JSONObject playerData = new JSONObject();
                savePlayerData(playerData, player);
                playersData.put(playerData);
            }
            completedTeamOneData.put(teamNameData);
            completedTeamOneData.put(playersData);
        }
        String completedTeamOneDataString = completedTeamOneData.toString();
        PrintWriter completedTeamOneFile = new PrintWriter("./data/" + fileName);
        completedTeamOneFile.write(completedTeamOneDataString);
        completedTeamOneFile.close();
    }

    // Effects: save second side teams of completed trades.
    public void saveCompletedTeamTwo(TradeList tl, String fileName) throws FileNotFoundException {
        JSONArray completedTeamTwoData = new JSONArray();
        for (Team completedTeam : tl.getCompletedTeamTwo()) {
            JSONObject teamNameData = new JSONObject();
            teamNameData.put("teamName", completedTeam.getTeamName());
            JSONArray playersData = new JSONArray();
            for (Player player : completedTeam.getPlayers()) {
                JSONObject playerData = new JSONObject();
                savePlayerData(playerData, player);
                playersData.put(playerData);
            }
            completedTeamTwoData.put(teamNameData);
            completedTeamTwoData.put(playersData);
        }
        String completedTeamTwoDataString = completedTeamTwoData.toString();
        PrintWriter completedTeamTwoFile = new PrintWriter("./data/" + fileName);
        completedTeamTwoFile.write(completedTeamTwoDataString);
        completedTeamTwoFile.close();
    }

    // Effects: save successes or failures of trades.
    public void saveSuccess(TradeList tl, String fileName) throws FileNotFoundException {
        JSONArray successesData = new JSONArray();
        for (boolean success : tl.getSuccess()) {
            JSONObject successData = new JSONObject();
            successData.put("success", success);
            successesData.put(successData);
        }
        String successesDataString = successesData.toString();
        PrintWriter successesFile = new PrintWriter("./data/" + fileName);
        successesFile.write(successesDataString);
        successesFile.close();
    }

    // Modifies: playerData
    // Effects: save the data of the player to playerData.
    public void savePlayerData(JSONObject playerData, Player player) {
        playerData.put("team", player.getTeam());
        playerData.put("name", player.getName());
        playerData.put("position", player.getPosition());
        playerData.put("age", player.getAge());
        playerData.put("height", player.getHeight());
        playerData.put("weight", player.getWeight());
        playerData.put("salary", player.getSalary());
    }

    // Effects: clear all the data in the files.
    public void clearData(String f1, String f2, String f3, String f4,
                          String f5, String f6, String f7) throws FileNotFoundException {
        PrintWriter file1 = new PrintWriter("./data/" + f1);
        PrintWriter file2 = new PrintWriter("./data/" + f2);
        PrintWriter file3 = new PrintWriter("./data/" + f3);
        PrintWriter file4 = new PrintWriter("./data/" + f4);
        PrintWriter file5 = new PrintWriter("./data/" + f5);
        PrintWriter file6 = new PrintWriter("./data/" + f6);
        PrintWriter file7 = new PrintWriter("./data/" + f7);
        JSONArray emptyArray = new JSONArray();
        String emptyArrayString = emptyArray.toString();
        file1.write(emptyArrayString);
        file2.write(emptyArrayString);
        file3.write(emptyArrayString);
        file4.write(emptyArrayString);
        file5.write(emptyArrayString);
        file6.write(emptyArrayString);
        file7.write(emptyArrayString);
        file1.close();
        file2.close();
        file3.close();
        file4.close();
        file5.close();
        file6.close();
        file7.close();
    }
}
