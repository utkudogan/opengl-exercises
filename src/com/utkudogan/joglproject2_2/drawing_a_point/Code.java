/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utkudogan.joglproject2_2.drawing_a_point;

/**
 *
 * @author bites
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
        setTitle("Chapter 2 - program 2");
        setSize(600, 400);
        myCanvas = new GLCanvas();
        myCanvas.addGLEventListener(this);
        this.add(myCanvas);
        this.setVisible(true);
    }

    public void display(GLAutoDrawable drawable) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        gl.glUseProgram(renderingProgram); //loads the program containing the two compiled shaders into the OpenGL pipeline stages (onto the GPU!)
//        gl.glPointSize(1.0f);
        gl.glPointSize(30.0f);
        gl.glDrawArrays(GL_POINTS, 0, 1); //The application also is responsible for telling OpenGL to construct 
        //triangles

    }

    public void init(GLAutoDrawable drawable) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        renderingProgram = createShaderProgram();
        gl.glGenVertexArrays(vao.length, vao, 0);
        gl.glBindVertexArray(vao[0]);
    }

    private int createShaderProgram() {
        GL4 gl = (GL4) GLContext.getCurrentGL();

        String vshaderSource[]
                = {"#version 430    \n", //opengl version
                    "void main(void) \n",
                    "{ gl_Position = vec4(0.0, 0.0, 0.0, 1.0); } \n"
                };

//        String fshaderSource[]
//                = {"#version 430    \n",
//                    "out vec4 color; \n", 
//                    "void main(void) \n",
//                    "{ color = vec4(0.0, 0.0, 1.0, 1.0); } \n"
//                };
//        
        String fshaderSource[]
                = {"#version 430    \n",
                    "out vec4 color; \n",
                    "void main(void) \n",
                    "{ if (gl_FragCoord.x < 295) color = vec4(1.0, 0.0, 0.0, 1.0); else color = vec4(0.0, 0.0, 1.0, 1.0); } \n"
                };

        int vShader = gl.glCreateShader(GL_VERTEX_SHADER); //create desired type of shader object 
        gl.glShaderSource(vShader, 3, vshaderSource, null, 0); //loads the GLSL code from the string array into the empty shader object
        gl.glCompileShader(vShader);

        int fShader = gl.glCreateShader(GL_FRAGMENT_SHADER);
        gl.glShaderSource(fShader, 4, fshaderSource, null, 0);
        gl.glCompileShader(fShader);

        /*The application then creates a program object named vfprogram and saves the integer ID that points to it. An OpenGL “program” object contains a series of compiled shaders, and here we see the commands glCreateProgram() to create the program object, glAttachShader() to attach each of the shaders to it, and then glLinkProgram() to request that the GLSL compiler ensure that they are compatible.
         */
        int vfprogram = gl.glCreateProgram();
        gl.glAttachShader(vfprogram, vShader);
        gl.glAttachShader(vfprogram, fShader);
        gl.glLinkProgram(vfprogram);

        gl.glDeleteShader(vShader);
        gl.glDeleteShader(fShader);
        return vfprogram;
    }

    public static void main(String[] args) {
        new Code();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void dispose(GLAutoDrawable drawable) {
    }
}
