package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@Disabled
@TeleOp(name = "Servo Test")
public class ServoTest extends LinearOpMode {
    Servo servo = null;
    double pos = 0;

    public void runOpMode(){

        servo = hardwareMap.get(Servo.class, "servo");

        servo.setPosition(0);


        waitForStart();
        servo.setPosition(1);


        while(opModeIsActive()){
            pos = servo.getPosition();

            telemetry.addData("Servo pos",pos);
            telemetry.update();
            if(pos==0){
                servo.setPosition(1);
            } else if(pos == 1){
                servo.setPosition(0);
            }
            sleep(1000);
        }

    }
}
