<?php
require "dbCon.php";

$id = $_POST['idSV'];
$thietbi = $_POST['thietbiSV'];
$trangthai = $_POST['trangthaiSV'];
$ghichu = $_POST['ghichuSV'];

$query = "UPDATE student SET thietbi = '$thietbi', trangthai = '$trangthai', ghichu = '$ghichu' WHERE id = '$id'";

if(mysqli_query($connect, $query)){
	//thành công
	echo "success";
}else{
	//lỗi
	echo "error";
}
?>