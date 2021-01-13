package com.mufg.assignment;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mufg.assignment.model.Input;
import com.mufg.assignment.model.Move;
import com.mufg.assignment.model.Output;
import com.mufg.assignment.model.Position;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
public class AssignmentController {

    private static Map<String,Integer> dirMap = new HashMap<String,Integer>() {{
        put("N", 0);
        put("W", 1);
        put("S", 2);
        put("E", 3);
    }};

    private static Map<Integer, String> numMap = new HashMap<Integer, String>() {{
        put(0, "N");
        put(1, "W");
        put(2, "S");
        put(3, "E");
    }};


    /**
     * This API is used to save the input, calculate the output based on input and then save
     * the output to a location
     *
     * @param input containing input data
     * @return Output
     * @throws IOException
     */
    @PostMapping(value = "/move", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Output> moveAndSavePosition(@RequestBody Input input) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();

        //save input to XML file
        xmlMapper.writeValue(new File("input.xml"), input);

        //calculate position based on input
        Output output = getOutput(input);

        //save output to XML file
        xmlMapper.writeValue(new File("output.xml"), output);
        return new ResponseEntity<Output>(output, HttpStatus.OK);
    }

    /**
     * This method is used to get the Output based on the given Input param
     *
     * @param input containing the current position and
     * @return Output based on input
     */
    private Output getOutput(Input input) {
        List<Move> moves = input.getMoves();
        moves.sort(Comparator.comparing(Move::getO));

        Position position = updatePositionBasedOnMove(moves, input.getPosition());

        Output output = new Output();
        output.setPosition(position);
        return output;
    }

    private Position updatePositionBasedOnMove(List<Move> moves, Position position) {
        String direction = position.getDirection();
        Integer X = Integer.valueOf(position.getX());
        Integer Y = Integer.valueOf(position.getY());
        for(Move move : moves){
           int dirNum = dirMap.get(direction);
           if(null != move.getL()){
               dirNum = (dirNum + move.getL()/90)%4;
           }
           else{
               dirNum = (4 + dirNum - move.getR()/90)%4;
           }
           direction = numMap.get(dirNum);
            switch (direction){
                case "N":
                    if(null != move.getF()){
                        Y+= move.getF();
                    }
                    else{
                        Y-= move.getB();
                    }
                    break;
                case "W":
                    if(null != move.getF()){
                        X-= move.getF();
                    }
                    else{
                        X+= move.getB();
                    }
                    break;
                case "S":
                    if(null != move.getF()){
                        Y-= move.getF();
                    }
                    else{
                        Y+= move.getB();
                    }
                    break;
                case "E":
                    if(null != move.getF()){
                        X+= move.getF();
                    }
                    else{
                        X-= move.getB();
                    }
                    break;
                default:
                    break;
            }
        }
        position.setDirection(direction);
        position.setX(String.valueOf(X));
        position.setY(String.valueOf(Y));
        return position;
    }


    /**
     * This get API is used to fetch the current position of the BOT
     *
     * @return the current position from saved XML
     * @throws IOException
     */
    @GetMapping(value = "/position", produces = "application/json" )
    public ResponseEntity<Output> getCurrentPosition() throws IOException {
        File file = new File("output.xml");
        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(new FileInputStream(file));
        Output output = xmlMapper.readValue(xml, Output.class);
        return new ResponseEntity<Output>(output, HttpStatus.OK);
    }

    public String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
