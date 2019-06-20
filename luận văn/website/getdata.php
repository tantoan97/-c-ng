<?php
require "dbCon.php";

$query ="SELECT * FROM student";

$data = mysqli_query($connect, $query);

// 1. Tạo class SinhVien
class SinhVien{
	function SinhVien($id,$thietbi,$trangthai,$ghichu){
		$this->ID=$id;
		$this->ThietBi=$thietbi;
		$this->TrangThai=$trangthai;
		$this->GhiChu=$ghichu;
	}
}
//2. Tạo mảng
$mangSV=array();
//3. Thêm phần tử vào mảng
while($row=mysqli_fetch_assoc($data)){
	array_push($mangSV, new SinhVien($row['id'], $row['thietbi'], $row['trangthai'], $row['ghichu']));
}

//4. Chuyển định dạng của mảng thành json
echo json_encode($mangSV);
?>