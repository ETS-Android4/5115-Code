package org.firstinspires.ftc.teamcode.Mecanum;

/* This OpMode runs motors in the following configuration:
       ////            \\\\
      /F1/ ------------ \F2\
     //// |            | \\\\
          |            |
          |            |
     \\\\ |            | ////
      \R1\ ------------ /R2/
       \\\\            ////
 */

//For left most position on red alliance

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Pogbot Blue1", group="Mecanum")
public class mBlue1 extends LinearOpMode {

    private DcMotor F1 = null;
    private DcMotor F2 = null;
    private DcMotor R1 = null;
    private DcMotor R2 = null;
    private DcMotor arm = null;
    private DcMotor spinner = null;
    private double F1speed = 1;
    private double F2speed = 0.5;
    private double R1speed = 0.5;
    private double R2speed = 1;

    @Override
    public void runOpMode() {
        // Set up the hardware
        telemetry.addData("Status", "Initialized");

        //GoBuilda 5202 motors go 12.3 inches in 537.6 encoder ticks
        F1 = hardwareMap.get(DcMotor.class, "F1");
        F2 = hardwareMap.get(DcMotor.class, "F2");
        R1 = hardwareMap.get(DcMotor.class, "R1");
        R2 = hardwareMap.get(DcMotor.class, "R2");
        spinner = hardwareMap.get(DcMotor.class, "spinner");

        F1.setDirection(DcMotorSimple.Direction.REVERSE);
        F2.setDirection(DcMotorSimple.Direction.REVERSE);
        R1.setDirection(DcMotorSimple.Direction.REVERSE);
        R2.setDirection(DcMotorSimple.Direction.REVERSE);
        spinner.setDirection(DcMotorSimple.Direction.FORWARD); //Might need to be changed

        F1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        F2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        R1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        R2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        waitForStart();

        xDrive(315); //forward
        turn(-100);
        yDrive(1800);

        //apply pressure to carousel
        F1.setPower(-0.3);
        F2.setPower(-0.3);
        R1.setPower(-0.3);
        R2.setPower(-0.3);

        spinner.setPower(0.5);

        sleep(1000);

        spinner.setPower(0);

        F1.setPower(0);
        F2.setPower(0);
        R1.setPower(0);
        R2.setPower(0);


//        F1.setTargetPosition(1000);
//        F2.setTargetPosition(-1000);    //right side motor
//        R1.setTargetPosition(1000);
//        R2.setTargetPosition(-1000); //right side motor
//
//        F1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        F2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        R1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        R2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        F1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        F2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        R1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        R2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//
//        F1.setPower(1);
//        F2.setPower(1);
//        R1.setPower(0.4);
//        R2.setPower(1);
//
//        while (opModeIsActive() && F1.isBusy()){
//            telemetry.addData("Status", "encoder test");
//            telemetry.addData("Encoder Position", "F1: "+F1.getCurrentPosition()+"  F2: "+F2.getCurrentPosition());
//            telemetry.addData("Encoder Position", "R1: "+R1.getCurrentPosition()+"  R2: "+R2.getCurrentPosition());
//            telemetry.update();
////                if(left.getCurrentPosition() >= left.getTargetPosition()){
////                    break;
////                } else {
////                    idle();
////                }
//            idle();
//        }
//
//
//
//        F1.setPower(0);
//        F2.setPower(0);
//        R1.setPower(0);
//        R2.setPower(0);
//
//        sleep(5000);



    }


    public void xDrive(int x){
        F1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        F2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        R1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        R2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        F1.setTargetPosition(x);
        F2.setTargetPosition(-x);    //right side motor
        R1.setTargetPosition(x);
        R2.setTargetPosition(-x); //right side motor

        F1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        F2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        F1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        F2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        R1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        R2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        F1.setPower(F1speed);
        F2.setPower(F2speed);
        R1.setPower(R1speed);
        R2.setPower(R2speed);

        while (opModeIsActive() && F1.isBusy()){
            telemetry.addData("Status", "encoder test");
            telemetry.addData("Encoder Position", "F1: "+F1.getCurrentPosition()+"  F2: "+F2.getCurrentPosition());
            telemetry.addData("Encoder Position", "R1: "+R1.getCurrentPosition()+"  R2: "+R2.getCurrentPosition());
            telemetry.update();
//                if(left.getCurrentPosition() >= left.getTargetPosition()){
//                    break;
//                } else {
//                    idle();
//                }
            idle();
        }




        F1.setPower(0);
        F2.setPower(0);
        R1.setPower(0);
        R2.setPower(0);

        sleep(1000);
    }

    public void yDrive(int x){
        //positive to the right, negative to the left for variable x
        F1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        F2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        R1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        R2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        F1.setTargetPosition(x);
        F2.setTargetPosition(x);    //right side motor
        R1.setTargetPosition(-x);
        R2.setTargetPosition(-x); //right side motor

        F1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        F2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        F1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        F2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        R1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        R2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        F1.setPower(F1speed);
        F2.setPower(F2speed);
        R1.setPower(R1speed);
        R2.setPower(R2speed);

        while (opModeIsActive() && F1.isBusy()){
            telemetry.addData("Status", "encoder test");
            telemetry.addData("Encoder Position", "F1: "+F1.getCurrentPosition()+"  F2: "+F2.getCurrentPosition());
            telemetry.addData("Encoder Position", "R1: "+R1.getCurrentPosition()+"  R2: "+R2.getCurrentPosition());
            telemetry.update();
//                if(left.getCurrentPosition() >= left.getTargetPosition()){
//                    break;
//                } else {
//                    idle();
//                }
            idle();
        }




        F1.setPower(0);
        F2.setPower(0);
        R1.setPower(0);
        R2.setPower(0);

        sleep(1000);
    }

    public void turn(int x){
        //positive right, negative left

        F1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        F2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        R1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        R2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        F1.setTargetPosition(x);
        F2.setTargetPosition(x);    //right side motor
        R1.setTargetPosition(x);
        R2.setTargetPosition(x); //right side motor

        F1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        F2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        F1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        F2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        R1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        R2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        F1.setPower(F1speed);
        F2.setPower(F2speed);
        R1.setPower(R1speed);
        R2.setPower(R2speed);

        while (opModeIsActive() && F1.isBusy()){
            telemetry.addData("Status", "encoder test");
            telemetry.addData("Encoder Position", "F1: "+F1.getCurrentPosition()+"  F2: "+F2.getCurrentPosition());
            telemetry.addData("Encoder Position", "R1: "+R1.getCurrentPosition()+"  R2: "+R2.getCurrentPosition());
            telemetry.update();
//                if(left.getCurrentPosition() >= left.getTargetPosition()){
//                    break;
//                } else {
//                    idle();
//                }
            idle();
        }




        F1.setPower(0);
        F2.setPower(0);
        R1.setPower(0);
        R2.setPower(0);

        sleep(1000);
    }
}
