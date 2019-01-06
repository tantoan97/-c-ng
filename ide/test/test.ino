#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include "DHT.h"
#define DHTPIN D1
#define DHTTYPE DHT11
DHT dht(DHTPIN,DHTTYPE);

// Thong so Wifi
const char* ssid = "Connectify-me";      //Ten wifi nha ban
const char* password = "tantoan97";   //Mat khau wifi nha ban
String server = "http://tantoanbk.tk";
//const char* host = "https://tantoanbk.000webhostapp.com"; //Trang web de lay du lieu
void setup() {
// Khoi dong Serial
  Serial.begin(115200);
  delay(10);
  pinMode(12,OUTPUT);
  pinMode(13,OUTPUT);
  pinMode(14,OUTPUT);
  pinMode(15,OUTPUT);
  digitalWrite(12,HIGH);
  digitalWrite(13,HIGH);
  digitalWrite(14,HIGH);
  digitalWrite(15,HIGH);
  ConnectWiFi();
}
void ConnectWiFi() {
  WiFi.mode(WIFI_OFF);
  delay(100);
  WiFi.mode(WIFI_STA);
  delay(100);
  Serial.print("Ket noi toi mang wifi ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.print(".");
  }
  Serial.println("Dia chi IP: ");
  Serial.println(WiFi.localIP());
}


void loop() {
 Get_Data();
 //Get_Weather();
 delay(1000);
}

void Get_Data() {
  HTTPClient GetStatus;
  String Link = server + "/getdata.php";
  GetStatus.begin(Link);
  int Code = GetStatus.GET();
  String Content = GetStatus.getString();
  GetStatus.end();
  Serial.println(Content);
    int RL12 = Content.indexOf("RL12");
    int RL13 = Content.indexOf("RL13");
    int RL14 = Content.indexOf("RL14");
    int RL15 = Content.indexOf("RL15");
    int bat12 = Content.indexOf("1",RL12 + 1);
    int tat12 = Content.indexOf("0",RL12 + 1);
    int tt12 = Content.indexOf("RL",RL12 + 1);
    int bat13 = Content.indexOf("1",RL13 + 1);
    int tat13 = Content.indexOf("0",RL13 + 1);
    int tt13 = Content.indexOf("RL",RL13 + 1);
    int bat14 = Content.indexOf("1",RL14 + 1);
    int tat14 = Content.indexOf("0",RL14 + 1);
    int tt14 = Content.indexOf("RL",RL14 + 1);
    int bat15 = Content.indexOf("1",RL15 + 1);
    int tat15 = Content.indexOf("0",RL15 + 1);
//    int tt15 = Content.indexOf("RL",RL15 + 1);
    if (RL12 != -1) {
      if (bat12 != -1 && bat12 < tt12){
        digitalWrite(12, HIGH);}
      if (tat12 != -1 && tat12 < tt12){
        digitalWrite(12, LOW);}
    } 
    if (RL13 != -1) {
      if (bat13 != -1 && bat13 < tt13){
        digitalWrite(13, HIGH);}
      if (tat13 != -1 && tat13 < tt13){
        digitalWrite(13, LOW);}
    }
    if (RL14 != -1) {
      if (bat14 != -1 && bat14 < tt14){
        digitalWrite(14, HIGH);}
      if (tat14 != -1 && tat14 < tt14){
        digitalWrite(14, LOW);}
    }
    if (RL15 != -1) {
      if (bat15 != -1){
        digitalWrite(15, HIGH);}
      if (tat15 != -1){
        digitalWrite(15, LOW);}
    }
}

void Get_Weather() {
  float h = dht.readHumidity();
  // Read temperature as Celsius (the default)
  float t = dht.readTemperature();
  if (isnan(h) || isnan(t)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  Serial.print("connecting to ");
  Serial.println(server);

  WiFiClient client;
  const int httpPort = 80;
  if (!client.connect(server, httpPort)) {
    Serial.println("connection failed");
    return;
  }
  
  String url = "/api/insert.php?temp=" + String(t) + "&hum="+ String(h);
  Serial.print("Requesting URL: ");
  Serial.println(url);
  
  client.print(String("GET ") + url + " HTTP/1.1\r\n" +
               "Host: " + server + "\r\n" + 
               "Connection: close\r\n\r\n");
  delay(500);
  
  while(client.available()){
    String line = client.readStringUntil('\r');
    Serial.print(line);
  }
  
  Serial.println();
  Serial.println("closing connection");
  delay(3000);
}
