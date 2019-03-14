package ru.vsu;

import ru.vsu.domains.SecurityGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static final String url = "jdbc:mysql://localhost:3306/mobks"
            + "?useSSL=false"
            + "&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";
    private List<SecurityGroup> securityGroupList;

    public List<SecurityGroup> getSecurityGroupList() {
        return securityGroupList;
    }

    public DB(int numberGroups){
        securityGroupList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            for (int i = 1; i <= numberGroups; i++){
                PreparedStatement preparedStatement = connection.prepareStatement("select * from security_classes where id = ?");
                preparedStatement.setString(1, String.valueOf(i));
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    securityGroupList.add(new SecurityGroup(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testConnection() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from security_classes");
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }
            System.out.println("We connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incConformityInGroups(String ruleId) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from claims where ruleId = ?");
            preparedStatement.setString(1, ruleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                findById(resultSet.getInt("classId")).incConformity();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private SecurityGroup findById(int id){
        SecurityGroup result = null;
        for (SecurityGroup securityGroup:securityGroupList){
            if (securityGroup.getId() == id){
                result = securityGroup;
                break;
            }
        }
        return result;
    }

    public void printResults(){
        for (SecurityGroup group:securityGroupList){
            System.out.println(group.getName() + " : ("
                    + group.getConformity() + " / " + group.getHowMuchPlusWeNeed() + ")");
        }
    }

    public void deleteOldData() {
        for(SecurityGroup securityGroup:securityGroupList){
            securityGroup.setConformity(0);
        }
    }
}
