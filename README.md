# Du_An_1
------------------------------------
# User:
  +userId <br>
  +userUN <br>
  +userPW 
# Product:
  +productId <br>
  +productName <br>
  +productCode
# Ticket:
  +ticketId <br>
  +ticketTitle <br>
  +ticketProduct
  <br>
    { <br>
      .productName: <br>
      .productCode  <br>
    }
    <br>
  +ticketDate <br>
  +ticketQuantity <br>
# Storage
  +storageId <br>
  +storageProduct
   <br>
    { <br>
      .productName: <br>
      .productCode  <br>
    }
    <br>
   +storageQuantity
  <br>
   +storageDate
   
   -----------------------------------------------------------------------------
   Cách thức tương tác giữa Activity
   -----------------------------------------------------------------------------
   _ Ở Giao diện Login khi đăng nhập bằng tài khoản Admin ta sẽ tiến hành vào MainActivity với quyền Admin <br>
    + Quyền Admin: <br>
      .<strong>Có thể </strong> thêm, xoá, sửa User đã tạo. <br>
      .<strong>Có thể </strong> thêm, xoá, sửa Ticket nhập xuất. <br>
      .<strong>Có thể</strong> xem danh sách hàng hoá trong kho <br>
      .<strong>Có thể</strong> xem thống kê Nhập xuất. <br>
    + Quyền User: <br>
      .<strong>Có thể</strong> thêm, xoá, sửa Ticket nhập xuất. <br>
      .<strong>Có thể</strong> Xem danh sách hàng hoá trong kho <br>
      .<strong>Không thể</strong> xem thống kê. <br>
      .<strong>Không thể</strong> quản lý người dùng <br>
      
      -------------------------------------------------------
      
_Nói tóm lại để cho đơn giản việc khác nhau giữa Admin và User là Admin có thể vào xem: <br>
  + Danh sách User
  + Xem thống kê.
  
      -------------------------------------------------------    
  Giao diện chính: <br>
  Khi click vào nút (+) là một FloatingAddButton sẽ mở lên AddTicketActivity. <br>
  Trong AddTicketActivity sẽ có 2 fragment mặc định khi vào sẽ là <strong> Thêm Hàng Hoá</strong> và vuốt qua hoặc chọn vào nút <strong> Xuất </strong> ta sẽ chuyển qua fragment <strong> Xuất Hàng Hoá </strong>. Và ở đây ta sẽ sử dụng 1 Fragment duy nhất sau đó tạo ra 2 fragment con nhập xuất bằng cách <br>
  <code>
    FragmentCha FragmentNhapCon = new FragmentCha(***); <br>
    FragmentCha FragmentXuatCon = new FragmentCha(***); <br>
  </code>
   <strong> *** </strong> Ở đây ta sẽ truyền vào Fragment Cha vì ở Ticket có 1 biến Type là Boolean, nếu Type = 1 sẽ là nhập và Type= 0 sẽ là Xuất. <br>
  <code>
    Fragment Cha sẽ có một Constructor đại loại như sau <br>
    FragmentCha(String type){ <br>
       if (type.equals("Nhap")){type= true} <br>
       else {type = false; } <br>
  } </code> <br>
    Bên trong FragmentCha sẽ bao gồm các Method xử lý cho 2 button Add và Cancel <br>
    Khi nhấn Add ta sẽ gọi vào TicketService và gọi hàm Add.<br>
    Việc xử lý hàm Add ở TicketService như sau:<br>
      .Validate <br>
      .Add <br>
  >>> Và quan trọng nhất, bên trong TicketService ta phải tạo <b> 2 Method sau:  </b> <br>
    + Một method sẽ gọi vào TicketDAO.getListNhap <br>
    + Một method sẽ gọi vào TicketDAO.getListXuat <br>
  Ở TicketService ta sẽ thêm vào 2 câu lệnh querry, 2 câu lệnh querry này sẽ xuất ra dữ liệu với điều kiện query <b> WHERE Type =? </b> và ta sẽ định nghĩa cho 2 method đó.
  <<<


 
