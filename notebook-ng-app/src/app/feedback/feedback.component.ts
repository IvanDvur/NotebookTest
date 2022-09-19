import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApiService} from "../shared/api.service";

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
  model: FeedbackDto = {
    name: '',
    email: '',
    feedback: ''
  };

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
  }

  public sendFeedback(): void {

    this.apiService.postFeedback(this.model).subscribe({
      next: (res) => {
        location.reload();
      },
      error: (err) => {
        alert("An error has occured")
      }
    })
  }
}

export interface FeedbackDto {
  name: string;
  email: string;
  feedback: string;
}
