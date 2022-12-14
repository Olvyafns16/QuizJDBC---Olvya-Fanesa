/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package koneksimysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author OLVYA
 */
public class KoneksiMySql {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/hr";
String user = "root";
String password = "";
try {
Class.forName(driver);
String query = "SELECT EMPLOYEE_ID, LAST_NAME, SALARY, MANAGER_ID FROM employees WHERE MANAGER_ID IN ('100','101','201')";
try (
Connection con =
DriverManager.getConnection(url, user, password);
Statement st = con.createStatement();
ResultSet rs = st.executeQuery(query)) {
int colNum = getColumnNames(rs);
if (colNum > 0) {
while (rs.next()) {
for (int i = 0; i < colNum; i++) {
if (i + 1 == colNum) {
System.out.println(rs.getString(i + 1));
} else {
System.out.print(rs.getString(i + 1) + ", ");
}  
}//endfor
}//endwhile
}//endif
} catch (SQLException e) {
System.out.println(e);
}//end try catch
}
catch (ClassNotFoundException e) {
System.out.println(e);
}
}
//end main
    public static int getColumnNames(ResultSet rs)
throws SQLException {
int numberOfColumns = 0;
if (rs != null) {
//create an object based on
// the Metadata of the result set
ResultSetMetaData rsMetaData = rs.getMetaData();
//Use the getColumn method to get
// the number of columns returned
numberOfColumns = rsMetaData.getColumnCount();
//get and print the column names,
// column indexes start from 1
for (int i = 1; i < numberOfColumns + 1; i++) {
String columnName = rsMetaData.getColumnName(i);
System.out.print(columnName + ", ");
}//endfor
}//endif
//place the cursor on a new line in the console
return numberOfColumns;
}//end method getColumnNames
}//end public KoneksiMySql