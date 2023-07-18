package com.zsc.example.nobody.jsrsey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/ekb/user")
public class UserResource {


    /**
     * http://127.0.0.1:1402/api/ekb/user/select
     *
     * @return
     */

    @GET
    @Path("select")
    public String getId() {
        return "zsc001";
    }


}
