// import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class InstrumentSpec {

  private Map<String, String> properties;

  public InstrumentSpec() {
    this.properties = new HashMap<>();
  }

  public InstrumentSpec(Map<String, String> properties) {
    // if (properties == null) {
      assert properties != null;
      this.properties = new HashMap<>(properties);
  //   } else {
  //     this.properties = new HashMap(properties);
  //   }
  }

  public Object getProperty(String propertyName) {
    return properties.get(propertyName);
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  // public boolean matches(InstrumentSpec otherSpec) {
  //   for (Iterator i = otherSpec.getProperties().keySet().iterator(); 
  //        i.hasNext(); ) {
  //     String propertyName = (String)i.next();
  //     if (!properties.get(propertyName).equals(
  //          otherSpec.getProperty(propertyName))) {
  //       return false;
  //     }
  //   }
  //   return true;
  // }

  public boolean matches(InstrumentSpec otheSpec) {
    for (String propertyName : otheSpec.getProperties().keySet()) {
      if (! properties.get(propertyName).equals(otheSpec.getProperty(propertyName))) {
        return false;
      }
    }
    return true;
  }
}
