package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutonomousSleep extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor spinner = null;


    @Override
    public void runOpMode() {

        // Send telemetry message to signify robot waiting;

        leftDrive = hardwareMap.get(DcMotor.class, "left");
        rightDrive = hardwareMap.get(DcMotor.class, "right");
        spinner = hardwareMap.get(DcMotor.class, "spinner");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        spinner.setDirection(DcMotor.Direction.FORWARD);


        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Operational");    //
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();


    }
    public void Forward(long sleeptime, double power) {

        leftDrive.setPower(-power);
        rightDrive.setPower(-power);
        sleep(sleeptime);

        leftDrive.setPower(1);
        rightDrive.setPower(1);

        sleep(50);

        leftDrive.setPower(0);
        rightDrive.setPower(0);


    }
    public void Reverse(long sleeptime, double power) {

        leftDrive.setPower(power);
        rightDrive.setPower(power);
        sleep(sleeptime);

        leftDrive.setPower(-1);
        rightDrive.setPower(-1);

        sleep(50);

        leftDrive.setPower(0);
        rightDrive.setPower(0);

    }
    public void Turn(String direction, long sleeptime, double power) {
        if (direction == "right") {
            leftDrive.setPower(0);
            rightDrive.setPower(power);
            sleep(sleeptime);

            leftDrive.setPower(0);
            rightDrive.setPower(0);
        } else if (direction == "left") {
            leftDrive.setPower(power);
            rightDrive.setPower(0);
            sleep(sleeptime);

            leftDrive.setPower(0);
            rightDrive.setPower(0);
        } else {
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            telemetry.addData("Error Log", "typo in Turn() function");
        }
    }
    public void TurnLeft (long sleeptime, double power) {
    }

}
