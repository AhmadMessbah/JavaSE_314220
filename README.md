Tasks :

    - oveisi    : Customer(id, name, family, username, password, phone, active)
    - bakhshi   : Branch(id, title, address, phone, area, active)
    - pasdar    : Contact(id, state, city, region, address,postalCode, phone, description)
    - jalilian  : Payment(id, title, amount, dateTime, description, paymentType(Check,Cash,Card))
    - aligholi  : Delivery(id, productName, address, dateTime, deliveryStatus(NotReady, Ready, Sent, Delivered))
    - rezaziian : Invoice(id, invoiceSerial, productName, quantity, price, dateTime, customerName)

Order :

    - model/entity
    - test
    - model/repository
    - model/service
    - test
    - controller
    - view
    - test
