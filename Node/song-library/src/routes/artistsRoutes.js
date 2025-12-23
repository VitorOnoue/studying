import express from "express";
import artistController from "../controllers/artistController.js";

const routes = express.Router();

routes.get("/artists", artistController.getAllArtists);
routes.get("/artists/:id", artistController.getArtistById);
routes.post("/artists", artistController.createArtist);
routes.put("/artists/:id", artistController.updateArtistById);
routes.delete("/artists/:id", artistController.deleteArtistById);

export default routes;