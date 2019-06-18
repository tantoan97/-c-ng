<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
 <title>Bảng điều khiển</title>
 <style>
  table {
   border-collapse: collapse;
   width: 100%;
   color: #588c7e;
   font-family: "Times New Roman";
   font-size: 25px;
   text-align: left;
     } 
  th {
   background-color: #588c7e;
   color: white;
    }
  tr:nth-child(even) {background-color: #f2f2f2}
 </style>
</head>
<body>
 <table>
 <tr>
  <th>ID</th>
  <th>Ngõ ra</th> 
  <th>Trạng thái</th> 
  <th>Thiết bị</th>
  <th>Sửa</th>
 </tr>
 <?php
$connect = mysqli_connect("localhost", "id8287156_tantoan97", "tantoan97", "id8287156_tantoan");
  // Check connection
  if ($connect->connect_error) {
   die("Connection failed: " . $connect->connect_error);
  } 
  $sql = "SELECT id, thietbi, trangthai, ghichu FROM student";
  $result = $connect->query($sql);
  if ($result->num_rows > 0) {
   // output data of each row
   while($row = $result->fetch_assoc()) {
    echo "<tr><td>" . $row['id'] . "</td>";
    echo "<td>" . $row['thietbi'] . "</td>";
    echo "<td>" . $row['trangthai'] . "</td>";
    echo "<td>" . $row['ghichu'] . "</td>";
    //Truyền tham số id tới trang update.php
    echo "<td><a href='update.php?id=" . $row['id'] . "'>Update</a></td>";
    echo"</tr>";
}
echo "</table>";
} else { echo "0 results"; }
$connect->close();
?>
</table>
</body>
</html>
