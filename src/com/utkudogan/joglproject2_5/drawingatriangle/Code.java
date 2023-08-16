/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utkudogan.joglproject2_5.drawingatriangle;

/**
 *
 * @author utkudogan
 */
import javax.swing.*;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GLContext;

public class Code extends JFrame implements GLEventListener {

    private GLCanvas myCanvas;
    private int renderingProgram;
    private int vao[] = new int[1];

    public Code() {
        setTitle("Chapter 2 - program 5");
        setSize(400, 200);
        myCanvas = new GLCanvas();
        myCanvas.addGLEventListener(this);
        this.add(myCanvas);
        this.setVisible(true);
    }

    public void display(GLAutoDrawable drawable) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        gl.glUseProgram(renderingProgram);
        gl.glDrawArrays(GL_TRIANGLES, 0, 3);
    }

    public void init(GLAutoDrawable drawable) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        String vertShader ="./src/com/utkudogan/joglproject2_5/drawingatriangle/vertShader.glsl";
        String fragShader = "./src/com/utkudogan/joglproject2_5/drawingatriangle/fragShader.glsl";
        renderingProgram = Utils.createShaderProgram(vertShader,fragShader);
        gl.glGenVertexArrays(vao.length, vao, 0);
        gl.glBindVertexArray(vao[0]);
    }

    public static void main(String[] args) {
        new Code();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void dispose(GLAutoDrawable drawable) {
    }
}
