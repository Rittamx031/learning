**Cách viết Dockerfile**

**Dockerfile** là tệp văn bản chứa các lệnh để tạo **Docker Image**. Dưới đây là cấu trúc cơ bản và cách viết:

---

### 1. **Cấu trúc cơ bản**
```dockerfile
# 1. Chọn Image cơ sở
FROM <base_image>

# 2. Cài đặt biến môi trường (tùy chọn)
ENV <key>=<value>

# 3. Copy mã nguồn vào Image
COPY <source_path> <destination_path>

# 4. Thiết lập thư mục làm việc
WORKDIR <working_directory>

# 5. Cài đặt các phụ thuộc (nếu cần)
RUN <command>

# 6. Định nghĩa lệnh thực thi chính (entry point)
CMD ["executable", "param1", "param2"]
```

---

### 2. **Ví dụ chi tiết**
Tạo Dockerfile cho một ứng dụng Node.js.

**Dockerfile:**
```dockerfile
# Sử dụng Node.js làm Image cơ sở
FROM node:14

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file package.json để cài đặt phụ thuộc
COPY package.json .

# Cài đặt các phụ thuộc
RUN npm install

# Sao chép toàn bộ mã nguồn vào container
COPY . .

# Mở cổng 3000 để truy cập ứng dụng
EXPOSE 3000

# Lệnh chạy ứng dụng
CMD ["npm", "start"]
```

---

### 3. **Giải thích các lệnh chính**
- **FROM:** Chỉ định Image cơ sở. Ví dụ: `node:14` hoặc `python:3.9`.
- **ENV:** Đặt biến môi trường, ví dụ: `ENV NODE_ENV=production`.
- **WORKDIR:** Thư mục làm việc bên trong container.
- **COPY:** Sao chép file hoặc thư mục từ máy chủ vào Image.
- **RUN:** Chạy lệnh khi tạo Image (ví dụ: cài đặt phần mềm).
- **CMD:** Lệnh chính để chạy khi container khởi động.

---

### 4. **Cách build và chạy Dockerfile**
- Build Docker Image từ Dockerfile:
  ```bash
  docker build -t <image_name> .
  ```
- Chạy container từ Image:
  ```bash
  docker run -p 3000:3000 <image_name>
  ```
