import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import traceroute.TracerouteItem;
public abstract class Traceroute
{
private Runtime run;
public Traceroute()
{
run = Runtime.getRuntime();
}
public ArrayList<TracerouteItem> traceroute(String destination)
{
ArrayList<TracerouteItem> result = new ArrayList<TracerouteItem>();
Process pr = null;
String cmd = getTracerouteCommand(destination);
try
{
pr = run.exec(cmd);
}
catch(IOException e)
{
e.printStackTrace();
}
BufferedReader buf = new BufferedReader(new InputStreamReader(
pr.getInputStream()));
String line = "";
try
{
while((line = buf.readLine()) != null)
{
TracerouteItem item = parse(line);
result.add(item);
}
}
catch(IOException e)
{ return null;
}
return result;
}
public abstract TracerouteItem parse(String line);
public abstract String getTracerouteCommand(String destination);
}



