import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RiddleDetailComponent } from './riddle-detail.component';

describe('RiddleDetailComponent', () => {
  let component: RiddleDetailComponent;
  let fixture: ComponentFixture<RiddleDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RiddleDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RiddleDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
