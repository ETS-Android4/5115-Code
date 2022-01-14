/*
* Place Robot on 3rd tile from the carousel,
*  with the left side lined up on the left edge of the tile
* */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Red Auto 1", group = "Linear Opmode")
public class RedAuto extends LinearOpMode{
    DcMotor left;
    DcMotor right;
    DcMotor spinner;
    boolean ran = false;

    double speed = 0.6;

    public void runOpMode(){
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
        spinner = hardwareMap.dcMotor.get("spinner");
        left.setDirection(DcMotor.Direction.REVERSE);
        spinner.setDirection(DcMotorSimple.Direction.FORWARD);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);





        waitForStart();
// THE FOLLOWING WAS FOR TESTING PURPOSES
//        left.setTargetPosition(250);
//        right.setTargetPosition(250);
//        left.setPower(speed);
//        right.setPower(speed);
//        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        while(left.isBusy() && right.isBusy()){
//            telemetry.addData("Status", "testing");
//            telemetry.addData("Encoder Position", "Left: "+left.getCurrentPosition()+"  Right: "+right.getCurrentPosition());
//            telemetry.update();
//            idle();
//        }
//        left.setPower(0);
//        right.setPower(0);

        //REAL CODE NOW
        encoders(250,250,"Forward 1");
        encoders(-650,650,"left turn");
        encoders(1800,1800,"forward 2");
        encoders(-100,-100,"fixing");
        spinner.setPower(0.4);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setPower(0.2); //keep pressure to the carousel
        right.setPower(0.3);
        sleep(5000);
        spinner.setPower(0);
        left.setPower(0);
        right.setPower(0);

        encoders(-500,-500, "reverse1");
        encoders(-300,300,"turn");
        encoders(-400,-400,"reverse");
        encoders(300,-300,"turn");
        encoders(-4000,-4000,"reverse to warehouse");


        telemetry.addData("Status","finished");
        telemetry.update();
        sleep(5000);
        //





//        while(opModeIsActive()){
//            //go forward 250 ticks
//            //turn right 90 degrees(1000 ticks)
//            //go forward 1000 ticks
////            sleep(1000);
////            sleep(1000);
////            sleep(2000);
////
////            break;
//        }

    }

    public void encoders(int targetLeft, int targetRight,String status){
        if(opModeIsActive()){
            left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            left.setTargetPosition(targetLeft);
            right.setTargetPosition(targetRight);
            left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("Status", status);
            telemetry.update();

            left.setPower(speed);
            right.setPower(speed);

            while (opModeIsActive() && left.isBusy() && right.isBusy()){
                telemetry.addData("Status", status);
                telemetry.addData("Encoder Position", "Left: "+left.getCurrentPosition()+"  Right: "+right.getCurrentPosition());
                telemetry.update();
//                if(left.getCurrentPosition() >= left.getTargetPosition()){
//                    break;
//                } else {
//                    idle();
//                }
                idle();
            }
//            while(opModeIsActive() && left.isBusy()){
//
//                if(left.getCurrentPosition() >= left.getTargetPosition()){
//                    break;
//                } else {
//                    idle();
//                }
//            }
            

            left.setPower(0);
            right.setPower(0);

            left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            sleep(1000);
        }

    }


}
