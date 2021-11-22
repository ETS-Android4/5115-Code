package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "test System", group = "Linear Opmode")
public class EncoderTest extends LinearOpMode {
    DcMotor motor;

    public void runOpMode(){
        motor = hardwareMap.dcMotor.get("left");
        motor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor.setTargetPosition(1000);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor.setPower(0.6);

        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        while(opModeIsActive()){
            int pos = motor.getCurrentPosition();
            int targetPos = motor.getTargetPosition();
            telemetry.addData("Encoder Pos: ", pos);
            telemetry.addData("Target Pos:", motor.getTargetPosition());
            telemetry.update();


            if(pos>= targetPos){
                motor.setPower(0);
                sleep(500); //wait for motor to come to a stop
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
        }






//        sleep(2000);



    }


}
