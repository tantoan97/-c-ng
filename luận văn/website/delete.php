<?php
require "dbCon.php";

$idsv = $_POST['idCuaSinhVien'];

$query = "DELETE FROM student WHERE id = '$idsv'";

if(mysqli_query($connect, $query)){
	//thành công
	echo "success";
}else{
	//lỗi
	echo "error";
}

?>