import mongoose from "mongoose";

const artistSchema = new mongoose.Schema({
    id: {
        type: mongoose.Schema.Types.ObjectId
    },
    name: {
        type: String,
        required: true
    },
    country: {
        type: String
    }

}, {
    versionKey: false
});

const artist = mongoose.model("artists", artistSchema);

export {
    artist,
    artistSchema
};