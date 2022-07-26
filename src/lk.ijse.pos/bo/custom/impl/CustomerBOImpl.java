package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl<customerDAO> implements CustomerBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
//    private final CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        for (Customer customer : all) {
            allCustomers.add(new CustomerDTO(customer.getCID(),customer.getCTitle(),customer.getCName(),customer.getCAddress(),customer.getCity(),customer.getProvince(),customer.getPostalCode()
            ));
        }
        return allCustomers;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCID(),dto.getCTitle(),dto.getCName(),dto.getCAddress(),dto.getCity(),dto.getProvince(),dto.getPostalCode()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCID(),dto.getCTitle(),dto.getCName(),dto.getCAddress(),dto.getCity(), dto.getProvince(), dto.getPostalCode()));
    }

    @Override
    public boolean customerExist(String CID) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(CID);
    }

    @Override
    public boolean deleteCustomer(String CID) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(CID);
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> searchCustomers(String search) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = customerDAO.searchCustomers(search);
        ArrayList<CustomerDTO> cusDto=new ArrayList<>();

        for (Customer customer : customers) {
            cusDto.add(new CustomerDTO(customer.getCID(),
                    customer.getCTitle(),
                    customer.getCName(),
                    customer.getCAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode()
            ));
        }
        return cusDto;
    }
}




