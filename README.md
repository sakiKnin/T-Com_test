docker compose build
docker compose up

GET http://localhost:8080/api/ccustomer => endpoint za dohvrat svih korisnika

POST http://localhost:8080/api/customer => endpoint za kreiranje novog korisnika

GET http://localhost:8080/api/customer/stats?start_date={start_data}&{end_date} => endpoint za kreiranje statistike

GET http://localhost:8080/api/cart => endpoint za dohvrat svih cart

POST http://localhost:8080/api/cart  => endpoint za kreiranje carta

req:{"_customer_id":"123.123","_cart_items":[{ "_product_id":"1212","_price_id":"3232-212","_action":"ADD"}]}

DELETE http://localhost:8080/api/cart?id={cart_id}  => endpoint za brisanje carta i pripadajućig itema

POST http://localhost:8080/api/cart-item => endpint za dodavnje novog cart itema

req: {"_cart_id":"123-123","_product_id":"1212","_price_id":"3232-212","_action":"ADD"}

DELETE http://localhost:8080/api/cart-item?id={cart_item_id} => endpint za brisanje cart itema







