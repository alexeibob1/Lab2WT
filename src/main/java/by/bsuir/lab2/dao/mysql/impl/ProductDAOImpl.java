package by.bsuir.lab2.dao.mysql.impl;

import by.bsuir.lab2.bean.Locale;
import by.bsuir.lab2.bean.Product;
import by.bsuir.lab2.bean.ProductLocalInfo;
import by.bsuir.lab2.bean.dto.LocalizedProductTO;
import by.bsuir.lab2.dao.ProductDAO;
import by.bsuir.lab2.dao.connection.ConnectionPool;
import by.bsuir.lab2.dao.connection.ConnectionPoolException;
import by.bsuir.lab2.dao.exception.DAOException;
import by.bsuir.lab2.dao.mysql.AbstractDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl extends AbstractDAO implements ProductDAO {
    public static final Logger LOGGER = LogManager.getLogger(ProductDAOImpl.class);
    private static final String ADD_PRODUCT = "INSERT INTO drug (`dosage`, `need_prescription`, `amount`, `price`) VALUES (?, ?, ?, ?)";
    private static final String ADD_PRODUCT_LOCAL_INFO = "INSERT INTO drug_local (`drug_id`, `locale`, `manufacturer`, `name`, `dosage_unit`, `form`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_PRODUCT = "SELECT `drug_id`, `dosage`, `need_prescription`, `amount`, `price`, `manufacturer`, `name`, `dosage_unit`, `form` "
            + "FROM `drug` " +
            "INNER JOIN `drug_local` ON `drug`.`id`=`drug_local`.`drug_id`" +
            " WHERE `drug_id` = ? AND `locale` = ?;";

    private static final String GET_ALL_PRODUCTS = "SELECT `drug`.`id`, `dosage`, `need_prescription`, `amount`, `price`, `manufacturer`, `name`, `dosage_unit`, `form` "
            + "FROM `drug` INNER JOIN `drug_local` ON `drug`.`id`=`drug_local`.`drug_id`" +
            " WHERE `locale` = ? ORDER BY `name`";

    @Override
    public int addProduct(LocalizedProductTO productLocal) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(ADD_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, productLocal.getDosage());
            stmt.setBoolean(2, productLocal.getIsNeedPrescription());
            stmt.setInt(3, productLocal.getAmount());
            stmt.setBigDecimal(4, productLocal.getPrice());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during creating new record in `drug` table in database.");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                ConnectionPool.getInstance().closeDBResources(rs, stmt);
            } catch (SQLException e) {
                LOGGER.warn("Exception during closing resources of the database.", e);
            }
        }
    }

    @Override
    public void addLocalProductInfo(List<ProductLocalInfo> productLocalInfoList, int productID) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(ADD_PRODUCT_LOCAL_INFO);
            for (ProductLocalInfo productInfo : productLocalInfoList) {
                stmt.setInt(1, productID);
                stmt.setString(2, productInfo.getLocale().toString());
                stmt.setString(3, productInfo.getManufacturer());
                stmt.setString(4, productInfo.getName());
                stmt.setString(5, productInfo.getDosageUnit());
                stmt.setString(6, productInfo.getDrugForm());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during adding a localized product info to table 'drug_local' in database.", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                ConnectionPool.getInstance().closeDBResources(stmt);
            } catch (SQLException e) {
                LOGGER.warn("Exception during closing resources of the database.", e);
            }
        }
    }

    @Override
    public Product getProduct(Locale locale, int productID) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_PRODUCT);
            stmt.setInt(1, productID);
            stmt.setString(2, locale.toString());
            rs = stmt.executeQuery();
            rs.next();
            return formProduct(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting product by id from database.", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                ConnectionPool.getInstance().closeDBResources(rs, stmt);
            } catch (SQLException e) {
                LOGGER.warn("Exception during closing resources of the database.", e);
            }
        }
    }

    @Override
    public List<Product> getProducts(Locale locale) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Product> products;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_ALL_PRODUCTS);
            stmt.setString(1, locale.toString());
            rs = stmt.executeQuery();
            products = formProducts(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting list of products from database.", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                ConnectionPool.getInstance().closeDBResources(rs, stmt);
            } catch (SQLException e) {
                LOGGER.warn("Exception during closing resources of the database.", e);
            }
        }

        if (products.isEmpty()) {
            return new ArrayList<>();
        }

        return products;
    }

    @Override
    public void updateProduct(Product product) throws DAOException {

    }

    private List<Product> formProducts(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            Product product = formProduct(rs);
            products.add(product);
        }
        return products;
    }

    private Product formProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setProductID(rs.getInt(1));
        product.setDosage(rs.getDouble(2));
        product.setIsNeedPrescription(rs.getBoolean(3));
        product.setAmount(rs.getInt(4));
        product.setPrice(rs.getBigDecimal(5));
        product.setManufacturer(rs.getString(6));
        product.setName(rs.getString(7));
        product.setDosageUnit(rs.getString(8));
        product.setDrugForm(rs.getString(9));
        return product;
    }
}
