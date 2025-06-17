import mongoose from "mongoose";

const songSchema = new mongoose.Schema({
    id: { type: mongoose.Schema.Types.ObjectId },
    title: { type: String, required: true },
    artist: { type: String, required: true },
    album: { type: String },
    length: { type: String, required: true },
}, { versionKey: false });

const song = mongoose.model("songs", songSchema);

export default song;