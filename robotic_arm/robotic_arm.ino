#include <WiFi.h>
#include <Servo.h>
#include <AccelStepper.h>

#define step 8
#define dir 9

const float stepsPerRevolution = 200;

char ssid[] = "TP-Link";
char pass[] = "ar77vdc!";
int status = WL_IDLE_STATUS;
Servo servo[5];
byte direction = 2;
bool stepperSt = false;
AccelStepper stepper(AccelStepper::DRIVER, step, dir);

void setMaxSpeedStepper(float MaxRPM){
  float Max_Speed_StepsPerSec = stepsPerRevolution * MaxRPM / 60; 
  stepper.setMaxSpeed(Max_Speed_StepsPerSec);
}

WiFiServer server(9541);
void setup() {
  Serial.begin(9600);
  for(byte i=0, pin = 2; i<5; i++, pin++){
    servo[i].attach(pin);
  }

  setMaxSpeedStepper(200);
  
  float AccelRPMperSec = 2000;
  float Accel_StepsPerSec2 = stepsPerRevolution * AccelRPMperSec / 60;
  stepper.setAcceleration(Accel_StepsPerSec2);

  stepper.setCurrentPosition(0);


  if (WiFi.status() == WL_NO_MODULE) {
    Serial.println("Communication with WiFi module failed!");
    while (true);
  }
  while (status != WL_CONNECTED) {
    Serial.print("Attempting to connect to Network named: ");
    Serial.println(ssid);

    status = WiFi.begin(ssid, pass);
    delay(3000);
  }
  server.begin();
  printWifiStatus();
}

void loop() {
  WiFiClient client = server.accept();
  if(client){
    Serial.println("New Client");
    while(client.connected()){
      stepper.runSpeed();  // call this on every loop to keep motor running

  if(client.available()) {
    String serv = client.readStringUntil('\n');
    int ser = serv.toInt();
    String ang = client.readStringUntil('\n');
    int angle = ang.toInt();

    if(ser == 6) {
      if(angle == 0) {
        stepper.setSpeed(0); // stop motor
      } else {
        float speed = stepsPerRevolution * abs(angle) / 60.0;
        if(angle < 0) speed = -speed;
        stepper.setSpeed(speed);
      }
      Serial.print("Stepper speed: ");
      Serial.println(angle);
    } else {
      if(ser >= 1 && ser <= 5) {
        servo[ser-1].write(angle);
        Serial.print("Servo ");
        Serial.print(ser);
        Serial.print(" set to ");
        Serial.println(angle);
      }
    }
  }
    }
    client.stop();
    Serial.println("Client disconnected");
  }
}

void printWifiStatus() {
  // print the SSID of the network you're attached to:
  Serial.print("SSID: ");
  Serial.println(WiFi.SSID());

  // print your board's IP address:
  IPAddress ip = WiFi.localIP();
  Serial.print("IP Address: ");
  Serial.println(ip);

  // print the received signal strength:
  long rssi = WiFi.RSSI();
  Serial.print("signal strength (RSSI):");
  Serial.print(rssi);
  Serial.println(" dBm");
  // print where to go in a browser:
  Serial.print("To see this page in action, open a browser to http://");
  Serial.println(ip);
}
