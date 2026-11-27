# Winlator DeX — Hướng dẫn tiếng Việt

Bản Winlator chỉ chạy trên Samsung DeX / chế độ desktop, ưu tiên làm việc
văn phòng: chuột tuyệt đối, không bao giờ bị kẹt chuột, có giao diện
tiếng Việt.

## Yêu cầu

Máy đang ở chế độ Samsung DeX hoặc có màn hình ngoài. Mở trên điện thoại
thường, ứng dụng sẽ báo và tự thoát.

## Chuột

- Trên DeX không tự giữ (capture) chuột ngoài — con trỏ chạy tuyệt đối
  như máy tính thật.
- Đẩy chuột ra ria màn hình để thoát capture (chỉ ở chế độ desktop).
- Nút nổi hiện khi đang capture; phím `Ctrl+Alt+G` thả chuột và mở menu;
  phím Back và các mục menu cũng tự thả chuột.
- Tốc độ chuột ăn theo tốc độ chuột của DeX trong Cài đặt hệ thống.

## Thanh thao tác nhanh

Nút bánh răng góc trên-phải bung ra: bàn phím, thả chuột, mở menu.

## Copy/paste

Mặc định trên DeX tự bật đồng bộ clipboard Android → Wine, đổi clipboard
bên ngoài là đẩy vào ngay. Tắt trong Cài đặt thì giữ nguyên tắt.

## Cấu hình khuyên dùng (Galaxy S25+ / Snapdragon 8 Elite)

- Màn hình container: 1920x1080 khi tạo mới trên DeX, có sẵn mức 2560x1440.
- Đồ họa: Turnip (tự chọn cho Adreno) + Gladio.
- DX wrapper: mặc định WineD3D (hợp app văn phòng nhất); cần 3D thì đổi DXVK từng máy.
- Khởi động: Normal (đủ dịch vụ) cho tương thích cao nhất.
- Box64: Performance. Biến môi trường: WINEESYNC=1,
  WINEDLLOVERRIDES=powershell.exe=d (tạo sẵn cho container mới trên DeX).

## Tiếng Việt

Cài đặt > Ngôn ngữ > Tiếng Việt. Chuỗi nào chưa dịch sẽ hiện tiếng Anh.
Ứng dụng Wine nhận LC_ALL=vi_VN.UTF-8 khi đã chọn tiếng Việt.

## Lấy log khi crash

Mỗi phiên chạy ghi vào `Documents/WinlatorDeX/logs.txt`. Crash của app nối thêm
vào `Documents/WinlatorDeX/crash.txt`. Gửi cả hai file khi báo lỗi.

## Lấy file APK

Vào mục Releases của repo, tải file APK ở bản `v*` mới nhất — do
GitHub Actions tự build khi gắn thẻ.

## Liên quan bản gốc

Các sửa DeX đã gửi về brunodev85/winlator-app#110. Repo này đi hướng
riêng chỉ-DeX (bỏ game mode, bỏ console log, không hỗ trợ điện thoại).
