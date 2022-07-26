package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> allItems = new ArrayList<>();
        while (rst.next()) {
            allItems.add(new Item
                    (rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            Double.parseDouble(rst.getString(4)),
                            rst.getInt(5)));
        }
        return allItems;
    }

    @Override
    public boolean delete(String Icode) throws SQLException, ClassNotFoundException {
        System.out.println("INNE BOIMZPL eke");
        return SQLUtil.executeUpdate("DELETE FROM Item WHERE Icode=?", Icode);
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        System.out.println("item Dao ek mn inne");
        System.out.println(entity.getIcode()+"  "+ entity.getDescription()+"  "+entity.getPackSize()+"  "+ entity.getUnitPrice() + "  "+ entity.getQtyOnHand());
        return SQLUtil.executeUpdate("INSERT INTO Item  VALUES (?,?,?,?,?)", entity.getIcode(), entity.getDescription(),entity.getPackSize(), entity.getUnitPrice(), entity.getQtyOnHand());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Item SET Description=?,PackSize=?, UnitPrice=?, QtyOnHand=? WHERE Icode=?", entity.getDescription(),entity.getPackSize(), entity.getUnitPrice(), entity.getQtyOnHand(), entity.getIcode());
    }

    @Override
    public Item search(String Icode) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item WHERE Icode=?", Icode);
        if (rst.next()) {
            return new Item
                    (rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            Double.parseDouble(rst.getString(4)),
                            rst.getInt(5));
        }
        return null;
    }

    @Override
    public boolean exist(String Icode) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT code FROM Item WHERE Icode=?", Icode).next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT Icode FROM Item ORDER BY Icode DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("Icode");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    public ArrayList<Item> searchitem(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = SQLUtil.executeQuery("SELECT * FROM item where Icode LIKE ? OR Description LIKE ? OR PackSize LIKE ? OR UnitPrice LIKE ? OR QtyOnHand LIKE ? ", enteredText, enteredText, enteredText, enteredText, enteredText);
        ArrayList<Item> list = new ArrayList<>();

        while (result.next()) {
            list.add(new Item(result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    Double.parseDouble(result.getString(4)),
                    result.getInt(5)


            ));
        }
        return list;
    }

}
