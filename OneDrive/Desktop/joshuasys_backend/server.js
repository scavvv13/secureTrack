const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");

const app = express();
const PORT = process.env.PORT || 5003;

// Middleware
app.use(express.json());
app.use(cors());

// MongoDB connection URI
const MONGODB_URI =
  "mongodb+srv://joshua:joshua-sys@joshua-sys.rfngant.mongodb.net/?retryWrites=true&w=majority&appName=joshua-sys";

// Connect to MongoDB
mongoose
  .connect(MONGODB_URI, { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => {
    console.log("Connected to MongoDB");
    // Your existing code here
  })
  .catch((err) => {
    console.error("Failed to connect to MongoDB", err);
  });

// Define API endpoints
// Fetch all documents
app.get("/api/documents", (req, res) => {
  console.log("Received request to /api/documents");
  Document.find()
    .then((documents) => {
      res.json(documents);
    })
    .catch((err) => {
      console.error(err);
      res.status(500).json({ message: "Server Error" });
    });
});

// Create a new document
app.post("/api/documents", (req, res) => {
  console.log("Received request to /api/documents");
  const { name, origin } = req.body;
  const newDocument = new Document({ name, origin });
  newDocument
    .save()
    .then((document) => {
      res.json(document);
    })
    .catch((err) => {
      console.error(err);
      res.status(500).json({ message: "Server Error" });
    });
});

// Start the server
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
