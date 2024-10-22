public class Delivery {

        private int id;
        private String productName;
        private String address;
        private LocalDateTime dateTime;
        private DeliveryStatus deliveryStatus;

        public Delivery(int id, String productName, String address, LocalDateTime dateTime, DeliveryStatus deliveryStatus) {
            this.id = id;
            this.productName = productName;
            this.address = address;
            this.dateTime = dateTime;
            this.deliveryStatus = deliveryStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public DeliveryStatus getDeliveryStatus() {
            return deliveryStatus;
        }

        public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
        }

        @Override
        public String toString() {
            return "Delivery{" +
                    "id=" + id +
                    ", productName='" + productName + '\'' +
                    ", address='" + address + '\'' +
                    ", dateTime=" + dateTime +
                    ", deliveryStatus=" + deliveryStatus +
                    '}';
        }
