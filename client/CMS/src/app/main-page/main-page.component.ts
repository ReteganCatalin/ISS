import {
  AfterViewInit,
  ApplicationRef,
  ChangeDetectorRef,
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {AddConferenceComponent} from './custom-components/add-conference/add-conference.component';
import {ConferenceService} from "../shared/services/conference.service";
import {Conference} from "../shared/models/Conference";
import {UserService} from "../shared/services/user.service";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit, AfterViewInit{
  @ViewChild('addConferenceForm', { read: ViewContainerRef }) entry: ViewContainerRef;
  conferenceList : Array<any>;
  visible: Number;

  constructor(private resolver: ComponentFactoryResolver,
              private changeDetectorRef: ChangeDetectorRef,
              private conferenceService: ConferenceService,
              private applicationRef: ApplicationRef, private userService: UserService) {
    this.conferenceList = [];
    this.visible = 1;
  }

  ngOnInit(): void {
    let uid = +sessionStorage.getItem('uid');
    this.userService.setUserID(uid);
    this.conferenceService.getConferenceNamesWithIDs().subscribe(response => {
      console.log(response);
      this.conferenceList = response;

    })
  }

  ngAfterViewInit() {
    const formFormFactory = this.resolver.resolveComponentFactory(AddConferenceComponent);
    this.entry.createComponent(formFormFactory);
    this.changeDetectorRef.detectChanges(); // nu stiu ce face line asta, da trebe ca sa nu isi dea naspa :/
  }

  selectVisible(i) {
    this.visible = i;
  }
}
