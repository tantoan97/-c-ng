#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

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
  digitalWrite(12,HIGH);
  digitalWrite(13,HIGH);
  digitalWrite(14,HIGH);
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
 delay(3000);
 
}

void Get_Data() {
  HTTPClient GetStatus;
  String Link = server + "/getdata.php";
  GetStatus.begin(Link);
  int Code = GetStatus.GET();
  String Content = GetStatus.getString();
  GetStatus.end();
  Serial.println(Content);
  
}
