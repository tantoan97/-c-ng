<?php
$username = "id8287156_tantoan97"; // Khai báo username
$password = "tantoan97";      // Khai báo password
$server   = "localhost";   // Khai báo server
$dbname   = "id8287156_tantoan";      // Khai báo database

//Nếu kết nối bị lỗi thì xuất báo lỗi và thoát.
$connect = mysqli_connect($server, $username, $password, $dbname);

//Nếu kết nối bị lỗi thì xuất báo lỗi và thoát.
if (!$connect) {
    die("Không kết nối :" . mysqli_connect_error());
    exit();
}

$id = $_POST['id'];
$thietbi = $_POST['thietbi'];
$trangthai = $_POST['trangthai'];
$ghichu = $_POST['ghichu'];

//Code xử lý, update dữ liệu vào table dựa theo điều kiện WHERE tại id = 1
$sql = "UPDATE student SET thietbi='$thietbi', trangthai='$trangthai', ghichu='$ghichu' WHERE id=$id";


if ($connect->query($sql) === TRUE) {
    //Nếu kết quả kết nối thành công, trở về trang view.
    header('Location: view.php');
} else {
    //Nếu kết quả kết nối không được thì trở về update.php đồng thời gán giá trị error=1, dựa theo giá trị này trang update.php có thể thông báo lỗi cần thiết.
    header('Location: update.php?error=1');
}

//Đóng kết nối database tintuc
$connect->close();