<?php
require "dbCon.php";

$thietbi = $_POST['thietbiSV'];
$trangthai = $_POST['trangthaiSV'];
$ghichu = $_POST['ghichuSV'];

$query = "INSERT INTO student VALUES(null, '$thietbi','$trangthai','$ghichu')";

if(mysqli_query($connect, $query)){
	//thành công
	echo "success";
}else{
	//lỗi
	echo "error";
}

?>