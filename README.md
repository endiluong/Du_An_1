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
    + Quyền User:
      .<strong>Có thể</strong> thêm, xoá, sửa Ticket nhập xuất. <br>
      .<strong>Có thể</strong> Xem danh sách hàng hoá trong kho <br>
      .<strong>Không thể</strong> xem thống kê. <br>
      .<strong>Không thể</strong> quản lý người dùng <br>
      
      -------------------------------------------------------
      
_Nói tóm lại để cho đơn giản việc khác nhau giữa Admin và User là Admin có thể vào xem: <br>
  + Danh sách User
  + Xem thống kê.
  
      -------------------------------------------------------    
    
 
