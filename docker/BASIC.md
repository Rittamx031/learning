**Sự khác biệt giữa Docker Images và Containers:**

- **Image:**
  - Là **bản mẫu** (blueprint) chứa mã nguồn và các công cụ cần thiết để chạy ứng dụng.
  - Được tạo **một lần** và dùng để tạo nhiều container.

- **Container:**
  - Là **phiên bản đang chạy** của image.
  - Chứa ứng dụng và môi trường để thực thi ứng dụng.

**Tóm lại:**
- **Image**: Mẫu thiết kế, chia sẻ được.
- **Container**: Ứng dụng chạy thực tế, tạo từ image.

--> **Image** là nền tảng, **Container** là sản phẩm hoạt động.
**Tạo và làm việc với Docker Images & Containers**

1. **Cách lấy Image:**
   - Tải từ **Docker Hub** (cộng đồng hoặc chính thức).
   - Tự tạo qua **Dockerfile**.

2. **Chạy Container từ Image:**
   - `docker run node`: Tạo container từ Image Node.
   - `docker ps -a`: Kiểm tra container đã tạo.

3. **Chế độ tương tác:**
   - `docker run -it node`: Truy cập shell tương tác trong container.

4. **Độc lập với hệ thống:**
   - Phiên bản phần mềm trong container **khác** trên máy chủ.

5. **Tạo nhiều container:**
   - Chạy nhiều lần lệnh `docker run node` để tạo nhiều container từ một Image.

**Tóm lại:**
- **Image:** Chứa mã & môi trường.
- **Container:** Phiên bản chạy thực tế từ Image.
**Hướng dẫn xây dựng Docker Image và Container từ Dockerfile**

### 1. **Tạo Docker Image từ Dockerfile**
- **Mở terminal** và chuyển đến thư mục chứa `Dockerfile`.
- Chạy lệnh sau để build Image:
  ```bash
  docker build -t <image_name> .
  ```
  - `-t <image_name>`: Đặt tên cho Image (tùy chọn).
  - `.`: Thư mục chứa Dockerfile (thường là thư mục hiện tại).

- Kết quả: Docker sẽ thực hiện từng bước trong Dockerfile, bao gồm:
  1. Lấy Image cơ sở (ví dụ: Node.js).
  2. Sao chép mã nguồn và cài đặt phụ thuộc.
  3. Thiết lập lệnh khởi động (CMD).

---

### 2. **Chạy Container từ Image**
- Chạy Container bằng Image vừa tạo:
  ```bash
  docker run <image_name>
  ```
- Nếu ứng dụng cần truy cập qua cổng (port), thêm tùy chọn `-p`:
  ```bash
  docker run -p 3000:80 <image_name>
  ```
  - `3000:80`:
    - **3000**: Cổng trên máy chủ cục bộ.
    - **80**: Cổng bên trong Container.

---

### 3. **Kiểm tra và quản lý Container**
- **Xem Container đang chạy:**
  ```bash
  docker ps
  ```
- **Dừng Container:**
  ```bash
  docker stop <container_name_or_id>
  ```
- **Xem tất cả Container (kể cả đã dừng):**
  ```bash
  docker ps -a
  ```

---

### 4. **Lưu ý về EXPOSE trong Dockerfile**
- Lệnh `EXPOSE <port>` trong Dockerfile chỉ mang tính **tài liệu**.
- Để truy cập Container qua cổng, phải sử dụng `-p` khi chạy lệnh `docker run`.

---

### 5. **Tóm tắt quá trình**
1. Viết **Dockerfile** để định nghĩa cách xây dựng Image.
2. Chạy lệnh `docker build` để tạo Image.
3. Sử dụng `docker run` để chạy Container từ Image.
4. Quản lý Container bằng các lệnh như `docker ps`, `docker stop`.
### **EXPOSE và Tính năng Tiện ích trong Docker**

#### 1. **Lệnh `EXPOSE` trong Dockerfile**
- **Mục đích:**
  - Ghi chú rằng một cổng (port) trong container sẽ được sử dụng bởi một tiến trình bên trong.
  - **Không tự động mở cổng** cho máy chủ cục bộ, chỉ mang tính tài liệu.

- **Thực tế:**
  - Phải sử dụng **`-p`** khi chạy `docker run` để ánh xạ cổng (port mapping).

- **Ví dụ:**
  ```dockerfile
  EXPOSE 80
  ```
  - Ghi chú rằng container sẽ sử dụng cổng 80, nhưng cần lệnh sau để ánh xạ:
  ```bash
  docker run -p 3000:80 <image_name>
  ```
  - Ánh xạ cổng 80 của container đến cổng 3000 của máy chủ cục bộ.

---

#### 2. **Shortcut cho ID**
- Với các lệnh yêu cầu ID (như `docker run`, `docker stop`, `docker ps`), bạn không cần nhập **toàn bộ ID**.
- **Chỉ cần vài ký tự đầu tiên**, miễn là đủ để Docker phân biệt duy nhất.

- **Ví dụ:**
  Nếu Image ID là `abcdefg`:
  ```bash
  docker run abc
  ```
  Nếu chỉ có một Image ID bắt đầu bằng `a`, bạn chỉ cần:
  ```bash
  docker run a
  ```

---

### **Tóm lại**
- **EXPOSE:** Là tài liệu, không bắt buộc, nhưng là **best practice**.
- **-p:** Bắt buộc để ánh xạ cổng khi chạy container.
- **Shortcut ID:** Tiện lợi, chỉ cần nhập đủ để phân biệt ID duy nhất.
### **Tóm tắt về Dockerfile, Image và Container**

1. **Dockerfile và Image**:
   - Dockerfile chứa các lệnh hướng dẫn Docker tạo ra một image.
   - Image là một snapshot của mã nguồn tại thời điểm build, sau khi tạo xong thì không thể thay đổi.

2. **Vấn đề với thay đổi mã nguồn**:
   - Khi thay đổi mã nguồn bên ngoài, những thay đổi này **không tự động được cập nhật** vào container nếu không rebuild image.

3. **Giải pháp**:
   - Cần chạy lại lệnh `docker build .` để tạo image mới với mã nguồn đã cập nhật, sau đó dùng `docker run` để khởi động container với image mới.

4. **Lưu ý quan trọng**:
   - Image là **đóng và bất biến** sau khi được tạo.
   - Phải rebuild lại image để phản ánh thay đổi mã nguồn.

5. **Cách tiếp cận tối ưu**:
   - Có các cách nhanh hơn (như bind mount volumes) để cập nhật mã nguồn mà không cần rebuild image liên tục.
### **Dịch về Kiến trúc Layer và Tối ưu hóa Dockerfile**

1. **Kiến trúc Layer trong Docker**:
   - Mỗi lệnh trong Dockerfile tạo ra một layer riêng biệt. Khi xây dựng lại image, chỉ những lệnh có thay đổi và tất cả các lệnh sau nó mới được thực thi lại.
   - Docker sử dụng cơ chế cache để tăng tốc quá trình xây dựng lại. Nếu không có thay đổi, Docker sẽ sử dụng kết quả cache từ các lệnh trước đó.

2. **Quản lý Layer**:
   - Một khi Docker xây dựng image, tất cả các lệnh trước đó tạo thành các layer và được lưu vào cache. Khi một layer thay đổi (ví dụ, khi mã nguồn thay đổi), Docker sẽ thực thi lại lệnh đó và các lệnh sau đó. Các lệnh trước đó sẽ không bị ảnh hưởng và được lấy từ cache.

3. **Vấn đề với `npm install`**:
   - Khi thay đổi mã nguồn, Docker vẫn thực thi lại `npm install` dù không cần thiết, vì Docker không phân tích sâu để xác định liệu việc thay đổi mã nguồn có ảnh hưởng đến các phụ thuộc hay không.
   - **Tối ưu hóa**: Để tránh việc `npm install` chạy lại không cần thiết, có thể thay đổi thứ tự các lệnh trong Dockerfile. Cụ thể, nên sao chép `package.json` trước, sau đó chạy `npm install`, và chỉ sau đó mới sao chép mã nguồn. Điều này giúp các layer liên quan đến `npm install` không bị vô hiệu hóa khi mã nguồn thay đổi.

4. **Quy trình tối ưu**:
   - Khi thay đổi mã nguồn, Docker sẽ chỉ thực thi lại các lệnh sau khi thay đổi, giúp tiết kiệm thời gian.
   - Nếu chỉ thay đổi mã nguồn và không thay đổi `package.json`, Docker sẽ không cần phải chạy lại `npm install`, giúp giảm thời gian build.

5. **Kết luận**:
   - Kiến trúc layer và cơ chế cache trong Docker giúp tăng tốc quá trình xây dựng lại image.
   - Hiểu cách các layer hoạt động là rất quan trọng để tối ưu hóa Dockerfile và quy trình phát triển.
### **Tóm tắt về Khái niệm Docker: Images và Containers**

1. **Image trong Docker**:
   - Image là một mẫu (template) chứa mã nguồn (code) của ứng dụng và môi trường cần thiết để thực thi mã đó.
   - Image được tạo ra từ một **Dockerfile**, nơi bạn chỉ định các hướng dẫn cụ thể: image cơ sở, mã nguồn và các phụ thuộc cần sao chép, các bước thiết lập như cài đặt npm, và các cổng cần mở.

2. **Container trong Docker**:
   - Container là một lớp mỏng (layer) chạy trên Image, nơi ứng dụng thực sự được thực thi. Nó sử dụng môi trường đã được xác định trong Image để chạy ứng dụng.
   - Container không sao chép lại mã nguồn hay môi trường từ Image. Thay vào đó, nó "sử dụng" các thành phần đã có trong Image và thêm lớp chạy ứng dụng (ví dụ, server Node.js).

3. **Hiệu quả của Docker**:
   - Docker cho phép nhiều container sử dụng cùng một Image, nhưng môi trường và mã nguồn chỉ tồn tại một lần trong Image, giúp tiết kiệm tài nguyên.
   - Các container độc lập và chạy riêng biệt, mỗi container chỉ sử dụng Image như một nguồn tài nguyên chung.

4. **Mục tiêu chính của Docker**:
   - Docker cung cấp môi trường cô lập để chứa ứng dụng và tất cả các công cụ/môi trường cần thiết để chạy ứng dụng đó, như Node.js hoặc các công cụ khác.
   - Docker đảm bảo rằng ứng dụng chạy trong một môi trường thống nhất, bất kể nó đang chạy ở đâu.

Với những khái niệm này, bạn có thể thấy Docker không chỉ giúp đóng gói ứng dụng, mà còn tạo ra môi trường chạy độc lập, dễ dàng tái tạo trên bất kỳ hệ thống nào mà không gặp phải vấn đề phụ thuộc hoặc cấu hình môi trường.
Chúng ta sẽ tìm hiểu cách quản lý và cấu hình **images** và **containers** trong Docker. Một lưu ý quan trọng: sử dụng `--help` để xem tất cả tùy chọn của bất kỳ lệnh Docker nào.

**Những gì sẽ học:**

- **Với Images:**
  - Gắn thẻ (tag) để đặt tên.
  - Liệt kê (list) và kiểm tra (inspect) images.
  - Xóa hoặc dọn dẹp images không cần thiết.

- **Với Containers:**
  - Đặt tên và cấu hình chi tiết.
  - Liệt kê containers (đang chạy và đã dừng).
  - Khởi động lại hoặc xóa containers khi không cần.
### **Các lệnh cơ bản để quản lý Docker Images và Containers:**

1. **Trợ giúp lệnh:**
   - `docker --help`: Hiển thị danh sách các lệnh chính của Docker.
   - Với mỗi lệnh, thêm `--help` để xem chi tiết các tùy chọn (e.g., `docker ps --help`).

2. **Quản lý Containers:**
   - `docker ps`: Hiển thị các containers đang chạy.
   - `docker ps -a`: Hiển thị tất cả containers, bao gồm cả đã dừng.
   - `docker start <container-ID|container-name>`: Khởi động lại container đã dừng mà không tạo container mới.
     - Sử dụng khi code, image hoặc dependency không thay đổi.
   - `docker run`: Tạo và chạy container mới từ image.

3. **So sánh `docker run` và `docker start`:**
   - `docker run`: Tạo container mới và khởi chạy, chiếm terminal để theo dõi logs.
   - `docker start`: Khởi động lại container đã dừng, không chiếm terminal.

4. **Kiểm tra container đang chạy:**
   - Sử dụng `docker ps` (không thêm `-a`) để kiểm tra container đang hoạt động.
   - Truy cập container qua cổng đã mở (e.g., `localhost:3000`).

---

**Lưu ý:** Khi dùng `docker start`, container khởi động nhưng không hiển thị log trực tiếp. Để tương tác hoặc xem log, cần dùng thêm lệnh hỗ trợ, sẽ đề cập trong phần sau.
### **Quản lý container với Docker: Chế độ Attached và Detached**

Docker cung cấp hai chế độ hoạt động chính khi chạy hoặc khởi động lại container: **Attached (gắn kết)** và **Detached (tách rời)**. Hiểu rõ hai chế độ này giúp bạn dễ dàng kiểm soát container hơn.

---

### **1. Sự khác biệt giữa chế độ Attached và Detached**
- **Attached (Mặc định khi dùng `docker run`)**
  - Terminal bị chiếm dụng, hiển thị logs và kết quả thực thi từ container.
  - Thích hợp khi cần theo dõi hoạt động của container trong thời gian thực.
  - Nhược điểm: Không thể chạy thêm lệnh khác trong terminal.

- **Detached (Mặc định khi dùng `docker start`)**
  - Container khởi động trong nền, terminal không bị chiếm dụng.
  - Phù hợp khi không cần theo dõi logs ngay lập tức.
  - Có thể khởi động container ở chế độ này với `-d` khi dùng `docker run`.

---

### **2. Các lệnh quản lý chế độ Attached và Detached**
- **Chuyển sang chế độ Detached:**
  - Thêm `-d` khi chạy container:
    ```bash
    docker run -d -p 8000:3000 <image-ID>
    ```
  - Container khởi động và trả về container ID mà không chiếm terminal.

- **Gắn lại (Attach) container đang chạy:**
  - Tìm container ID hoặc tên với `docker ps`.
  - Dùng lệnh `docker attach` để gắn vào container:
    ```bash
    docker attach <container-ID|container-name>
    ```

- **Xem logs mà không cần gắn:**
  - Xem toàn bộ logs với:
    ```bash
    docker logs <container-ID|container-name>
    ```
  - Theo dõi logs mới (chế độ **follow**) với `-f`:
    ```bash
    docker logs -f <container-ID|container-name>
    ```

---

### **3. Chạy lại container trong chế độ Attached**
- Sử dụng `-a` với lệnh `docker start` để khởi động container đã dừng ở chế độ Attached:
  ```bash
  docker start -a <container-ID|container-name>
  ```

---

### **4. Tóm tắt lệnh cơ bản**
- **Chạy container (mặc định Attached):**
  ```bash
  docker run -p 8000:3000 <image-ID>
  ```
- **Chạy container trong Detached:**
  ```bash
  docker run -d -p 8000:3000 <image-ID>
  ```
- **Gắn lại container:**
  ```bash
  docker attach <container-ID>
  ```
- **Xem logs:**
  ```bash
  docker logs <container-ID>
  ```
- **Theo dõi logs:**
  ```bash
  docker logs -f <container-ID>
  ```
Theo mặc định, nếu bạn chạy một container mà không sử dụng tùy chọn `-d`, bạn sẽ chạy trong **chế độ gắn kết (attached mode)**.

Nếu bạn đã khởi động container ở **chế độ tách rời (detached mode)** (tức là với tùy chọn `-d`), bạn vẫn có thể gắn kết (attach) vào nó sau đó mà không cần khởi động lại container bằng lệnh sau:

```bash
docker attach CONTAINER
```

Lệnh này sẽ **gắn kết bạn vào một container đang chạy** với ID hoặc tên là `CONTAINER`.
Cho đến thời điểm này, chúng ta đã làm việc với ứng dụng Node.js mẫu, chạy một máy chủ web. Quả thật, phát triển web và vận hành máy chủ web hoặc các tiến trình xử lý yêu cầu đầu vào là một trong những lý do chính mà Docker được sử dụng rộng rãi. Tuy nhiên, Docker không chỉ giới hạn ở việc này.

### Ví dụ khác: Ứng dụng Python

Để minh họa, chúng ta có một ứng dụng Python đơn giản (Python 3). Ứng dụng này không tạo ra máy chủ web mà chỉ yêu cầu người dùng nhập vào hai số (`min` và `max`), kiểm tra tính hợp lệ, sau đó tính và xuất ra một số ngẫu nhiên trong khoảng đó.

Thay vì cài đặt Python trực tiếp trên máy, ta có thể Dockerize ứng dụng này. Đây là cách thực hiện:

---

### Tạo Dockerfile

1. **Sử dụng Python làm base image**:
   - Trên Docker Hub, ta tìm thấy image chính thức của Python.
   - Chúng ta sẽ sử dụng nó làm cơ sở cho ứng dụng này.

2. **Cấu hình thư mục làm việc**:
   ```dockerfile
   WORKDIR /app
   ```

3. **Sao chép mã nguồn**:
   ```dockerfile
   COPY . /app
   ```

4. **Chạy ứng dụng Python**:
   ```dockerfile
   CMD ["python", "rng.py"]
   ```

---

### Build và Chạy Image

1. **Build image**:
   ```bash
   docker build -t python-rng .
   ```

2. **Chạy container**:
   ```bash
   docker run python-rng
   ```

---

### Tương tác với Container

Khi chạy container, chúng ta sẽ thấy lỗi nếu không cấu hình đúng, vì ứng dụng yêu cầu nhập từ người dùng. Docker mặc định chạy ở chế độ **gắn kết đầu ra (attached)**, nhưng không chấp nhận đầu vào. Để sửa lỗi này, sử dụng cờ sau:

- **Chế độ tương tác**:
  ```bash
  docker run -it python-rng
  ```
  - `-i`: Kích hoạt chế độ tương tác, cho phép nhập dữ liệu.
  - `-t`: Tạo một pseudo-terminal.

Bây giờ, bạn có thể nhập giá trị `min` và `max`, nhận kết quả ngẫu nhiên từ ứng dụng.

---

### Khởi động lại Container

1. **Khởi động lại ở chế độ gắn kết đầy đủ**:
   ```bash
   docker start -ai <CONTAINER_ID>
   ```
   - `-a`: Gắn kết đầu ra.
   - `-i`: Kích hoạt nhập liệu.

2. **Xem lại log của Container**:
   ```bash
   docker logs <CONTAINER_ID>
   ```

---

### Kết luận

Docker không chỉ dành cho máy chủ web hay các tiến trình dài hạn. Bạn có thể Dockerize các ứng dụng nhỏ, tiện ích đơn giản như ứng dụng Python này. Nếu cần tương tác, Docker cũng hỗ trợ đầy đủ thông qua các chế độ gắn kết và tương tác.
