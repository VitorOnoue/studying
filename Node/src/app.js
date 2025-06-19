import express from "express";
import databaseConnection from "./config/dbConnect.js";
import routes from "./routes/index.js";

const connection = await databaseConnection();

connection.on("error", (err) => {
    console.error("connection error: " + err);
});

connection.once("open", () => {
    console.log("database connection created successfully");
});

const app = express();
routes(app);

export default app;