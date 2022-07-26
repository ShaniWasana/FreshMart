package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> allCustomers = new ArrayList<>();
        while (rst.next()) {
            allCustomers.add(new Customer
                    (rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5),
                            rst.getString(6),
                            rst.getString(7)));
        }
        return allCustomers;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Customer (CID,CTitle,CName,CAddress,City,Province,PostalCode) VALUES (?,?,?,?,?,?,?)", entity.getCID(), entity.getCTitle(),entity.getCName(), entity.getCAddress(),entity.getCity(),entity.getProvince(),entity.getPostalCode());
    }


    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Customer SET CTitle=?,CName=?, CAddress=?,City=?,Province=?,PostalCode=? WHERE CID=?", entity.getCTitle(),entity.getCName(), entity.getCAddress(),entity.getCity(),entity.getProvince(),entity.getPostalCode(), entity.getCID());
    }

    @Override
    public Customer search(String CID) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer WHERE CID=?", CID);
        if (rst.next()) {
            return new Customer
                    (rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5),
                            rst.getString(6),
                            rst.getString(7));
        }
        return null;
    }


    @Override
    public boolean exist(String CID) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT CID FROM Customer WHERE CID=?", CID).next();
    }


    @Override
    public boolean delete(String CID) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Customer WHERE CID=?", CID);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT CID FROM Customer ORDER BY CID DESC LIMIT 1;");
        if (rst.next()) {
            String CID = rst.getString("CID");
            int newCustomerId = Integer.parseInt(CID.replace("CID-", "")) + 1;
            return String.format("CID-%03d", newCustomerId);
        } else {
            return "CID-001";
        }
    }


    @Override
    public ArrayList<Customer> searchCustomers(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = SQLUtil.executeQuery("SELECT * FROM Customer where CID LIKE ? OR CTitle LIKE ? OR CName LIKE ? OR CAddress LIKE ? OR City LIKE ? OR Province LIKE ? OR PostalCode LIKE ? ", enteredText, enteredText, enteredText, enteredText, enteredText, enteredText, enteredText);
        ArrayList<Customer> list = new ArrayList<>();

        while (result.next()) {
            list.add(new Customer(result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7)

            ));
        }
        return list;
    }
}

