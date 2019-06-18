<?php
$username = "id8287156_tantoan97"; // Khai báo username
$password = "tantoan97";      // Khai báo password
$server   = "localhost";   // Khai báo server
$dbname   = "id8287156_tantoan";      // Khai báo database

// Kết nối database tintuc
$connect = mysqli_connect($server, $username, $password, $dbname);

//Nếu kết nối bị lỗi thì xuất báo lỗi và thoát.
if (!$connect) {
    die("Không kết nối :" . mysqli_connect_error());
    exit();
}

//Lấy dữ liệu từ view.php bằng phương thức GET.
if(isset($_GET['id'])){
    $id = $_GET['id'];
    $sql     = "SELECT * FROM student WHERE id='$id'";
    $ket_qua = $connect->query($sql);

    while ($row = $ket_qua->fetch_array(MYSQLI_ASSOC)) {
        $thietbi = $row['thietbi'];
        $trangthai = $row['trangthai'];
        $ghichu = $row['ghichu'];
?>

<!-- Truyền dữ liệu vào form. -->
<form action="process.php" method="post">
    <meta charset="UTF-8"  lang="vi">
    <table>
        <tr>
            <th>ID:</th>
            <td>
                <input type="hidden" name="id" value="<?php echo $id; ?>">
                <?php echo $id; ?>
            </td>
        </tr>
        <tr>
            <th>Ngõ ra:</th>
            <td><input type="text" name="thietbi" value="<?php echo $thietbi; ?>"></td>
        </tr>

        <tr>
            <th>Trạng thái:</th>
            <td><input type="binary" name="trangthai" value="<?php echo $trangthai; ?>"></td>
        </tr>

        <tr>
            <th>Thiết bị:</th>
            <td><input type="text" name="ghichu" value="<?php echo $ghichu; ?>"></td>
        </tr>

    </table>
    <button type="submit">Xác nhận</button></button>
</form>

<?php 
    } //Đóng vòng lặp while.
} //Đóng câu lệnh if.

//Đóng kết nối database tintuc
$connect->close();
?>