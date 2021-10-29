package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.tfod.*;
import com.qualcomm.robotcore.hardware.DcMotor;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous System", group = "Autonomous")
//@Disabled
public class AutonomousDrive extends LinearOpMode {
    //declare the vars
    private DcMotor left = null;
    private DcMotor right = null;
    private DcMotor spinner = null;

    @Override
    public void runOpMode() {
        //telemetry stuff
        telemetry.addData("Status", "Started");
        telemetry.update();

        //vars are getting matched to their internal names and stuff
        right = hardwareMap.get(DcMotor.class, "right");
        left = hardwareMap.get(DcMotor.class, "left");
        spinner = hardwareMap.get(DcMotor.class, "spinner");

        //TESTING THING
        waitForStart();
        drive(12, 0.5);
        sleep(2000);
        halt();
        sleep(1000);
        endMotion();

    }
    /*
    Here are our other functions we will be calling
     */

    //Moves the robot forward x inches at y speed
    public void drive (double Inches, double Speed) {

        double Diameter = 6.28;
        double EncoderTurns = 288;
        double DesiredPos = Inches * EncoderTurns / Diameter;
        resetEncoders();

        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        right.setTargetPosition((int) DesiredPos);
        left.setTargetPosition((int) -DesiredPos);

        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(Speed);
        left.setPower(Speed);
    }

    //Goes reverse x inches for y speed
    public void reverse (double Inches, double Speed) {

        double Diameter = 6.28;
        double EncoderTurns = 288;
        double DesiredPos = Inches * EncoderTurns / Diameter;
        resetEncoders();

        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        right.setTargetPosition((int) DesiredPos);
        left.setTargetPosition((int) -DesiredPos);

        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(Speed);
        left.setPower(Speed);
    }
    //Makes the robot turn right for x inches at y speed
    public void rightDirection(double Inches , double Speed) {

        double Diameter = 6.28;
        double EncoderTurns = 288;
        double DesiredPos = Inches * EncoderTurns / Diameter;

        resetEncoders();
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        right.setTargetPosition((int) DesiredPos);
        left.setTargetPosition((int) -DesiredPos);

        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(Speed);
        left.setPower(Speed);

    }
    //Makes the robot turn left for x inches at y speed
    public void leftDirection (double Inches , double Speed) {

        double Diameter = 6.28;
        double EncoderTurns = 288;
        double DesiredPos = Inches * EncoderTurns / Diameter;

        resetEncoders();

        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        right.setTargetPosition((int) -DesiredPos);
        left.setTargetPosition((int) DesiredPos);

        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(Speed);
        left.setPower(Speed);

    }

    //This is how the encoders work OWO
    public void encoderTurn(double Inches, double Speed, int SleepTime,String Direction) {

        double Diameter = 11.21;
        double EncoderTurns = 288;
        double DesiredPos = Inches * EncoderTurns / Diameter;

        if (Direction == "RIGHT") {
            right.setTargetPosition((int) -DesiredPos);
            left.setTargetPosition((int) -DesiredPos);

        } else if (Direction == "LEFT") {
            right.setTargetPosition((int) DesiredPos);
            left.setTargetPosition((int) DesiredPos);
        } else {
            right.setTargetPosition(0);
            left.setTargetPosition(0);

        }

        right.setPower(Speed);
        left.setPower(Speed);


        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        sleep(SleepTime);

        resetEncoders();

    }

    //This controls the spinner
    public void spinner(String position, int SleepTime) {

        sleep(SleepTime);

        if (position == "ON") {
            spinner.setPower(-.5);
            sleep(300);

        } else if (position == "OFF") {
            spinner.setPower(.5);
            sleep(400);
            spinner.setPower(0);

        }
    }

    //quickly stops
    public void halt() {
        int Inches = 1;
        int Speed = 1;

        double Diameter = 6.28;
        double EncoderTurns = 288;
        double DesiredPos = Inches * EncoderTurns / Diameter;

        resetEncoders();
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        right.setTargetPosition((int) DesiredPos);
        left.setTargetPosition((int) -DesiredPos);

        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(Speed);
        left.setPower(Speed);
    }


        //Resets the encoder values
    public void resetEncoders() {
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    //This stops all motion in the bot
    public void endMotion() {
        left.setPower(0);
        right.setPower(0);
        spinner.setPower(0);
    }

}