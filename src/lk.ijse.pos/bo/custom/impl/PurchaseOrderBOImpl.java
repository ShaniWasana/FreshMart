package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.bo.custom.PurchaseOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.dao.custom.QueryDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);// hide the object creation logic through the factory
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailDAO orderDetailsDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);


    @Override
    public boolean deleteOrder(String CID) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(CID);
    }

    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {

        //Transaction
        Connection connection = DBConnection.getDbConnection().getConnection();
        //if order id already exist
        if (orderDAO.exist(dto.getOid())) {

        }
        connection.setAutoCommit(false);
        System.out.println("ORDERBOIMPL EKE INNE"+dto.getOid());

        boolean save = orderDAO.save(new Order(dto.getOid(), dto.getDate(), dto.getCID()));



        if (!save) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailDTO detailDTO : dto.getOrderDetail()) {
            boolean save1 = orderDetailsDAO.save(new OrderDetail(detailDTO.getOid(), detailDTO.getIcode(), detailDTO.getOquantity(), detailDTO.getDiscount()));
            if (!save1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //Search & Update Item
            ItemDTO item = searchItem(detailDTO.getIcode());
            item.setQtyOnHand(item.getQtyOnHand() - detailDTO.getOquantity());

            //update item
            boolean update = itemDAO.update(new Item(item.getIcode(), item.getDescription(),item.getPackSize(),item.getUnitPrice(), item.getQtyOnHand()));

            if (!update) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }




    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer ent = customerDAO.search(id);
        return new CustomerDTO(ent.getCID(), ent.getCTitle(), ent.getCName(),ent.getCAddress(),ent.getCity(),ent.getProvince(),ent.getPostalCode());
    }

    @Override
    public ItemDTO searchItem(String Icode) throws SQLException, ClassNotFoundException {
        Item ent = itemDAO.search(Icode);
        return new ItemDTO(ent.getIcode(), ent.getDescription(),ent.getPackSize(), ent.getUnitPrice(), ent.getQtyOnHand());
    }

    @Override
    public boolean checkItemIsAvailable(String Icode) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(Icode);
    }

    @Override
    public boolean checkCustomerIsAvailable(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer ent : all) {
            allCustomers.add(new CustomerDTO(ent.getCID(),ent.getCTitle(), ent.getCName(), ent.getCAddress(),ent.getCity(),ent.getProvince(),ent.getPostalCode()
            ));
        }
        return allCustomers;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        for (Item ent : all) {
            allItems.add(new ItemDTO(ent.getIcode(), ent.getDescription(), ent.getPackSize(), ent.getUnitPrice(), ent.getQtyOnHand()));
        }
        return allItems;
    }

}

