<?php
require "dbCon.php";
if ($connect->connect_error){
	die("Connection failed:" . $connect->connect_error);
}
$sql = "SELECT trangthai FROM student WHERE thietbi = 'RLA'";
$result = $connect->query($sql);
if (mysqli_num_rows($result)>0){
	while ($row = $result->fetch_assoc()){
		echo $row["trangthai"];
	}
}
$sql = "SELECT trangthai FROM student WHERE thietbi = 'RLB'";
$result = $connect->query($sql);
if (mysqli_num_rows($result)>0){
	while ($row = $result->fetch_assoc()){
		echo $row["trangthai"];
	}
}
$connect->close();
?>