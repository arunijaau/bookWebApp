/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jaau.bookwebapp.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Aruni
 */
public class AuthorDao implements IAuthorDao {

    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private DataAccess db;
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";

    public AuthorDao(String driverClass, String url, String userName, String password, DataAccess db) {

        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
        setDb(db);
    }

    public int addAuthor(List<String> colNames, List<Object> colValues) throws ClassNotFoundException, SQLException,
            IllegalArgumentException {
        //No need to do validation because an exception will be thrown if failed.
//        if(colNames == null || colValues == null){
//            throw new IllegalArgumentException("Must provide column names and column values.");
//        }
        db.openConnection(driverClass, url, userName, password);

        int recsAdded = db.createRecord(AUTHOR_TBL, colNames, colValues);

        db.closeConnection();
        return recsAdded;
    }

    public int updateAuthor(List<String> colNames, List<Object> colValues, Object pkValue) throws ClassNotFoundException, SQLException {
        db.openConnection(driverClass, url, userName, password);

        int recsUpdated = db.updateRecord(AUTHOR_TBL, colNames, colValues, AUTHOR_PK, pkValue);

        db.closeConnection();
        return recsUpdated;
    }

    public final int removeAuthorById(Integer id) throws ClassNotFoundException, IllegalArgumentException, SQLException {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("id must be an Integer greater than 0");

        }
        db.openConnection(driverClass, url, userName, password);

        int recsDeleted = db.deleteRecordById(AUTHOR_TBL, AUTHOR_PK, id);
        db.closeConnection();

        return recsDeleted;
    }

    public final Author getAuthorById(String colValue) throws ClassNotFoundException, SQLException, 
            IllegalArgumentException {
        if (colValue == null || colValue.isEmpty()) {
            throw new IllegalArgumentException("Column value must be provided.");
        }
        db.openConnection(driverClass, url, userName, password);

        Author author = new Author();
        Map<String, Object> record = db.findRecordById(AUTHOR_TBL, AUTHOR_PK, Integer.parseInt(colValue));

        Object objRecId = record.get("author_id");
        Integer recId = objRecId == null
                ? 0 : Integer.parseInt(objRecId.toString());
        author.setAuthorId(recId);
        
        Object objName = record.get("author_name");
        String authorName = objName == null ? "" : objName.toString();
        author.setAuthorName(authorName);

        Object objRecAdded = record.get("date_added");
        Date recAdded = objRecAdded == null ? null : (Date) objRecAdded;
        author.setDateAdded(recAdded);

        db.closeConnection();
        
        return author;
    }

    @Override
    public final List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException {

        db.openConnection(driverClass, url, userName, password);

        List<Author> list = new Vector<>();
        List<Map<String, Object>> rawData = db.getAllRecords(AUTHOR_TBL, 0);
        Author author = null;

        for (Map<String, Object> rec : rawData) {
            author = new Author();

            Object objRecId = rec.get("author_id");
            Integer recId = objRecId == null
                    ? 0 : Integer.parseInt(objRecId.toString());
            author.setAuthorId(recId);

            Object objName = rec.get("author_name");
            String authorName = objName == null ? "" : objName.toString();
            author.setAuthorName(authorName);

            Object objRecAdded = rec.get("date_added");
            Date recAdded = objRecAdded == null ? null : (Date) objRecAdded;
            author.setDateAdded(recAdded);

            list.add(author);
        }

        db.closeConnection();
        return list;
    }

    public DataAccess getDb() {
        return db;
    }

    public void setDb(DataAccess db) {
        this.db = db;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        IAuthorDao dao = new AuthorDao(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", "admin",
                new MySqlDataAccess()
        );

        int recsDeleted = dao.removeAuthorById(2);

        List<Author> list = dao.getListOfAuthors();

        for (Author a : list) {
            System.out.println(a.getAuthorId() + ", "
                    + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }
    }

}
