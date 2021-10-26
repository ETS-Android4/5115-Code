package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous System", group = "Autonomous")
//@Disabled
public class AutonomousDrive extends OpMode {
    //declare the vars
    private DcMotor left = null;
    private DcMotor right = null;
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }
    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        telemetry.addData("Status", "Started");
        telemetry.update();
    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        telemetry.addData("Status", "Stopped");
        telemetry.update();
    }
}