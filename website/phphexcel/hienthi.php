<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
 <title>Bảng dự đoán</title>
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
  <th>Hour</th>
  <th>Temperature</th> 
  <th>Humidity</th> 
  <th>Solar</th>
  <th>Rain</th>
  <th>Sunlight</th>
 </tr>
 <?php
$conn = mysqli_connect("localhost", "id8287156_tantoan97", "tantoan97", "id8287156_tantoan");
  // Check connection
  if ($conn->connect_error) {
   die("Connection failed: " . $conn->connect_error);
  } 
  $sql = "SELECT hour, temperature, humidity, solar, rain, sunlight FROM output";
  $result = $conn->query($sql);
  if ($result->num_rows > 0) {
   // output data of each row
   while($row = $result->fetch_assoc()) {
    echo "<tr><td>".$row["hour"]."</td><td>".$row["temperature"]."</td><td>".$row["humidity"]."</td><td>".$row["solar"]."</td><td>".$row["rain"]."</td><td>".$row["sunlight"]."</td></tr>";
}
echo "</table>";
} else { echo "0 results"; }
$conn->close();
?>
</table>
</body>
</html>