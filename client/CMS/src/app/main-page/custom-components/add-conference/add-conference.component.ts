import {Component, OnInit} from '@angular/core';
import { Conference} from '../../../shared/models/Conference';
import {HttpClient} from '@angular/common/http';
import {ConferenceData} from "../../../shared/models/ConferenceData";

@Component({
  selector: 'app-add-conference',
  templateUrl: './add-conference.component.html',
  styleUrls: ['./add-conference.component.css']
})
export class AddConferenceComponent implements OnInit {
  name: string;
  startDate: string;
  endDate: string;
  proposalDeadline: string;
  paperDeadline: string;
  callForPaper: string;
  about: string;
  format: string;
  byteFileLocation: string;
  selectedImageFiles: FileList;
  imageFileUpload: File;
  constructor(private http: HttpClient) { }

  ngOnInit(): void {}

  createConference() {
    const conference = new Conference();
    const conferenceData = new ConferenceData();
    console.log(this.name,
      new Date(this.startDate),
      new Date(this.endDate),
      new Date(this.proposalDeadline),
      new Date(this.paperDeadline));

    conference.setAttributes(
        0,
        this.name,
        new Date(this.startDate),
        new Date(this.endDate),
        new Date(this.proposalDeadline),
        new Date(this.paperDeadline));
    conferenceData.setAttributes(
      conference.conferenceID,
      this.callForPaper,
      this.about,
      this.format,
      this.byteFileLocation
      );
    console.log(conferenceData);
    this.http.post<Conference>('http://localhost:8081/conferences', conference).subscribe(conferenceResult => {
      console.log(conferenceResult);
      conferenceData.setAttributes(
        conferenceResult.conferenceID,
        this.callForPaper,
        this.about,
        this.format,
        this.byteFileLocation
      );
      this.http.post<ConferenceData>('http://localhost:8081/conference_data', conferenceData).subscribe(result => {
        console.log(result);
        this.imageFileUpload = this.selectedImageFiles.item(0);
        const imageForm: FormData = new FormData();
        imageForm.append("id",conferenceData.conferenceID.toString());
        imageForm.append("file",this.imageFileUpload);
        this.http.post<Blob>('http://localhost:8081/conference_data_file', imageForm).subscribe();
      });

    });
  }
  changeImageFormat() {
    console.log(this.byteFileLocation);
    this.format = this.byteFileLocation.slice(this.byteFileLocation.lastIndexOf("."), this.byteFileLocation.length);
  }
  selectImageFile(event) {
    this.selectedImageFiles = event.target.files;
  }
}
