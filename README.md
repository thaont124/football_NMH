Module:
- Phân công trọng tài
- Thống kê lượng khán giả theo trận

- Thêm database:


  -- Tạo dữ liệu cho bảng Season
INSERT INTO football.season (season_name) VALUES
('2024-2025'),
('2025-2026');

-- Tạo dữ liệu cho bảng Round (mỗi mùa có 13 vòng đấu)
INSERT INTO football.round (round_number, season_id) VALUES
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1),
(11, 1), (12, 1), (13, 1), -- 13 vòng cho mùa 2024-2025
(1, 2), (2, 2), (3, 2), (4, 2), (5, 2), (6, 2), (7, 2), (8, 2), (9, 2), (10, 2),
(11, 2), (12, 2), (13, 2); -- 13 vòng cho mùa 2025-2026

-- Tạo dữ liệu cho bảng Team (tổng cộng 14 đội tham gia)
INSERT INTO football.team (team_name, hometown) VALUES
('CLB Hà Nội', 'Hà Nội'),
('CLB Sài Gòn', 'Sài Gòn'),
('CLB Đà Nẵng', 'Đà Nẵng'),
('CLB Hải Phòng', 'Hải Phòng'),
('CLB Quảng Ninh', 'Quảng Ninh'),
('CLB TP. Hồ Chí Minh', 'TP. Hồ Chí Minh'),
('CLB Bình Dương', 'Bình Dương'),
('CLB Hòa Phát Hà Nội', 'Hà Nội'),
('CLB Than Quảng Ninh', 'Quảng Ninh'),
('CLB Sông Lam Nghệ An', 'Nghệ An'),
('CLB SHB Đà Nẵng', 'Đà Nẵng'),
('CLB Nam Định', 'Nam Định'),
('CLB Viettel', 'Hà Nội'),
('CLB Hoàng Anh Gia Lai', 'Gia Lai');

-- Tạo dữ liệu cho bảng Stadium (sân vận động ở Việt Nam)
INSERT INTO football.stadium (stadium_name, address) VALUES
('Sân vận động Mỹ Đình', 'Nam Từ Liêm, Hà Nội'),
('Sân vận động Thống Nhất', 'Quận 1, TP. Hồ Chí Minh'),
('Sân vận động Hòa Xuân', 'Hòa Xuân, Đà Nẵng'),
('Sân vận động Lạch Tray', 'Ngô Quyền, Hải Phòng'),
('Sân vận động Cẩm Phả', 'Cẩm Phả, Quảng Ninh'),
('Sân vận động Gò Đậu', 'Thủ Dầu Một, Bình Dương'),
('Sân vận động Hàng Đẫy', 'Hàng Đẫy, Hà Nội'),
('Sân vận động Cẩm Phả', 'Cẩm Phả, Quảng Ninh'),
('Sân vận động Vinh', 'TP. Vinh, Nghệ An'),
('Sân vận động Hòa Xuân', 'Hòa Xuân, Đà Nẵng'),
('Sân vận động Hòa Xuân', 'Hòa Xuân, Đà Nẵng'),
('Sân vận động Hàng Đẫy', 'Hàng Đẫy, Hà Nội'),
('Sân vận động Vinh', 'TP. Vinh, Nghệ An'),
('Sân vận động Pleiku', 'Pleiku, Gia Lai');

-- Tạo dữ liệu cho bảng Game (mỗi vòng đấu có 5 trận đấu)
-- Giả sử các trận đấu diễn ra trong một ngày cố định (2024-04-15 18:00:00)
INSERT INTO football.game (home_team_id, away_team_id, time, round_id) VALUES
(1, 2, '2024-04-15 18:00:00', 1), (3, 4, '2024-04-15 12:00:00', 1), (5, 6, '2024-04-15 14:00:00', 1), (7, 8, '2024-04-15 15:00:00', 1), (9, 10, '2024-04-15 16:00:00', 1),
(11, 12, '2024-04-15 18:00:00', 2), (13, 14, '2024-04-16 18:00:00', 2), (1, 3, '2024-04-16 18:00:00', 2), (2, 4, '2024-04-16 18:00:00', 2), (5, 7, '2024-04-16 18:00:00', 2),
(6, 8, '2024-04-16 18:00:00', 3), (9, 11, '2024-04-16 18:00:00', 3), (10, 12, '2024-04-17 18:00:00', 3), (13, 1, '2024-04-17 18:00:00', 3), (14, 2, '2024-04-17 18:00:00', 3);


INSERT INTO football.season_team (season_id, team_id, stadium_id) VALUES
(1, 1, 1), (1, 2, 2), (1, 3, 3), (1, 4, 4), (1, 5, 5), (1, 6, 6), (1, 7, 7), (1, 8, 8), (1, 9, 9), (1, 10, 10),
(1, 11, 11), (1, 12, 12), (1, 13, 13), (1, 14, 14),
(2, 1, 1), (2, 2, 2), (2, 3, 3), (2, 4, 4), (2, 5, 5), (2, 6, 6), (2, 7, 7), (2, 8, 8), (2, 9, 9), (2, 10, 10),
(2, 11, 11), (2, 12, 12), (2, 13, 13), (2, 14, 14);


-- Tiếp tục thêm dữ liệu cho vé
INSERT INTO football.Ticket (ticket_code, stand, ticket_row, gate, seat, price, status, game_id, stadium_id)
SELECT 
    CONCAT('TCKT00', t.id), -- Tạo ticketCode mới với id tăng dần
    'A', -- Giả sử stand là A cho tất cả vé
    FLOOR(RAND() * 10) + 1, -- Hàng ngẫu nhiên từ 1 đến 10
    'A', -- Cổng A cho tất cả vé
    LPAD(FLOOR(RAND() * 50) + 1, 2, '0'), -- Ghế ngẫu nhiên từ 01 đến 50
    50.0, -- Giá vé
    IF(FLOOR(RAND() * 2) = 0, 'Available', 'Sold'), -- Trạng thái vé ngẫu nhiên Available hoặc Sold
    FLOOR(RAND() * 15) + 1, -- game_id ngẫu nhiên từ 1 đến 15
    FLOOR(RAND() * 14) + 1 -- stadium_id ngẫu nhiên từ 1 đến 14
FROM
    (SELECT 1 AS id
    UNION SELECT 2
    UNION SELECT 3
    UNION SELECT 4
    UNION SELECT 5
    UNION SELECT 6
    UNION SELECT 7
    UNION SELECT 8
    UNION SELECT 9
    UNION SELECT 10
    UNION SELECT 11
    UNION SELECT 12
    UNION SELECT 13
    UNION SELECT 14
    UNION SELECT 15) t
CROSS JOIN
    (SELECT 1 AS dummy UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) d; -- Lặp lại 5 lần để tạo ra tổng cộng 50 dòng



-- Tạo dữ liệu cho bảng Referee
INSERT INTO football.referee (referee_name, dob, hometown, year_experience, status, referee_code) VALUES
('Trần Văn A', '1980-05-15', 'Hà Nội', 10, 1, 'REF001'),
('Nguyễn Thị B', '1985-09-20', 'Sài Gòn', 8, 1, 'REF002'),
('Phạm Văn C', '1988-03-10', 'Đà Nẵng', 6, 1, 'REF003'),
('Lê Thị D', '1982-07-05', 'Hải Phòng', 7, 1, 'REF004'),
('Hoàng Văn E', '1978-12-25', 'Quảng Ninh', 9, 1, 'REF005'),
('Nguyễn Văn F', '1975-11-30', 'TP. Hồ Chí Minh', 11, 1, 'REF006'),
('Trần Thị G', '1987-04-18', 'Bình Dương', 5, 1, 'REF007'),
('Vũ Văn H', '1983-08-22', 'Hà Nội', 8, 1, 'REF008'),
('Đinh Thị I', '1979-10-10', 'Quảng Ninh', 6, 1, 'REF009'),
('Hoàng Văn K', '1986-06-08', 'Nghệ An', 9, 1, 'REF010'),
('Trần Thị L', '1981-03-03', 'Đà Nẵng', 7, 1, 'REF011'),
('Vũ Văn M', '1984-09-28', 'Nam Định', 8, 1, 'REF012'),
('Nguyễn Văn N', '1977-01-20', 'Hà Nội', 10, 1, 'REF013'),
('Trần Thị A', '1990-05-15', 'Hà Nội', 10, 1, 'REF014'),
('Nguyễn Văn B', '1985-08-20', 'Hồ Chí Minh', 12, 1, 'REF015'),
('Phạm Thị C', '1988-11-10', 'Đà Nẵng', 8, 1, 'REF016'),
('Hoàng Văn D', '1992-03-25', 'Hải Phòng', 6, 1, 'REF017'),
('Trần Văn E', '1987-09-12', 'Cần Thơ', 9, 1, 'REF018'),
('Lê Thị F', '1991-07-18', 'Huế', 11, 1, 'REF019'),
('Nguyễn Thanh G', '1989-04-30', 'Nha Trang', 7, 1, 'REF020'),
('Vũ Thị H', '1986-12-05', 'Vũng Tàu', 13, 1, 'REF021'),
('Đặng Văn I', '1993-06-08', 'Quy Nhơn', 5, 1, 'REF022'),
('Mai Thị K', '1990-01-20', 'Phan Thiết', 10, 1, 'REF023'),
('Hoàng Văn L', '1984-10-14', 'Bắc Ninh', 14, 1, 'REF024'),
('Nguyễn Thị M', '1987-02-28', 'Long Xuyên', 8, 1, 'REF025'),
('Trần Văn N', '1991-08-03', 'Buôn Ma Thuột', 9, 1, 'REF026'),
('Lê Thị O', '1985-11-22', 'Sóc Trăng', 11, 1, 'REF027'),
('Phạm Văn P', '1988-07-09', 'Phú Yên', 12, 1, 'REF028'),
('Nguyễn Thị Q', '1994-04-15', 'Cà Mau', 6, 1, 'REF029'),
('Vũ Văn R', '1989-09-05', 'Thanh Hóa', 7, 1, 'REF030'),
('Trần Thị S', '1986-05-01', 'Bạc Liêu', 13, 1, 'REF031'),
('Nguyễn Văn T', '1992-03-10', 'Lạng Sơn', 8, 1, 'REF032'),
('Lê Thanh U', '1983-12-18', 'Quảng Bình', 15, 1, 'REF033');

